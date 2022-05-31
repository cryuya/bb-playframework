package controllers;

import models.Comments;

import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import io.ebean.Finder;
import com.google.common.collect.Lists;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;

import static play.libs.Scala.asScala;

@Security.Authenticated(Secured.class)
public class HomeController extends Controller {
	private Finder<Integer, Comments> finder = new Finder<>(Comments.class);
	private Form<CommentData> form;
	private MessagesApi messagesApi;
	private List<Comments> comments = Lists.newArrayList();

	@Inject
	public HomeController(FormFactory formFactory, MessagesApi messagesApi) {
		this.form = formFactory.form(CommentData.class);
		this.messagesApi = messagesApi;
	}

	public Result topPage(Http.Request request) {
		this.comments = finder.all();

		return ok(
			views.html.index.render(
				request.session().get("name").orElse("guest"), 
				request.session().get("id").orElse("id"),
				asScala(this.comments), this.form, this.form, 
				request, 
				this.messagesApi.preferred(request)
			)
		);
	}

	public Result createComment(Http.Request request) {
		Form<CommentData> boundForm = form.bindFromRequest(request);
		CommentData data = boundForm.get();

		String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Comments c = new Comments(Integer.parseInt(request.session().get("id").orElse("0")), data.getComment(), data.getTitle(), data.getComment(), nowDate, nowDate);
		c.save();
		return redirect("/").flashing("", "Comment added!");
	}

	public Result deleteComment(Http.Request request) {
		Form<CommentData> boundForm = form.bindFromRequest(request);
		CommentData data = boundForm.get();

		finder.deleteById(data.getId());
		return redirect("/").flashing("", "delete!");
	}
} 