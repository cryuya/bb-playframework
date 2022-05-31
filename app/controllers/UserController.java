package controllers;

import models.Users;
import models.Comments;

import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import io.ebean.Finder;
import com.google.common.collect.Lists;
import java.util.List;
import javax.inject.Inject;

import static play.libs.Scala.asScala;

@Security.Authenticated(Secured.class)
public class UserController extends Controller {
	private Finder<Integer, Users> finder = new Finder<>(Users.class);
	private Finder<Integer, Comments> commentsFinder = new Finder<>(Comments.class);
	private MessagesApi messagesApi;
	private Form<UserData> form;
	private Form<CommentData> commentForm;
	private List<Comments> comments = Lists.newArrayList();
	private Users user;

	@Inject
	public UserController(FormFactory formFactory, MessagesApi messagesApi) {
		this.form = formFactory.form(UserData.class);
		this.commentForm = formFactory.form(CommentData.class);
		this.messagesApi = messagesApi;
	}

	public Result userPage(Http.Request request) {
		this.comments = 
			commentsFinder.query()
				.where()
					.eq("user_id", Integer.parseInt(request.session().get("id").orElse("0")))
				.findList()
			;
		;

		return ok(views.html.user.render(asScala(this.comments), this.form, request, this.messagesApi.preferred(request)));
	}

	public Result updateUserInfo(Http.Request request) {
		Form<UserData> boundForm = form.bindFromRequest(request);
		UserData data = boundForm.get();

		finder.update()
			.set("name", data.getName())
			.set("password", data.getPassword())
			.where()
				.eq("id", Integer.parseInt(request.session().get("id").orElse("0")))
				.update()
		;

		return redirect("/user").flashing("", "updated!");
	}

	public Result deleteComment(Http.Request request) {
		Form<CommentData> boundForm = commentForm.bindFromRequest(request);
		CommentData data = boundForm.get();

		finder.deleteById(data.getId());
		return redirect("/user").flashing("", "delete!");
	}
}