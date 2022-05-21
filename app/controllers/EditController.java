package controllers;

import models.Comments;

import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import io.ebean.Finder;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;

import static play.libs.Scala.asScala;

public class EditController extends Controller {
	private Finder<Integer, Comments> finder = new Finder<>(Comments.class);
	private Form<CommentData> form;
	private MessagesApi messagesApi;
	private List<Comments> comments = com.google.common.collect.Lists.newArrayList();
	private List<Comments> searchedComments = com.google.common.collect.Lists.newArrayList();
	private Comments comment;

	@Inject
	public EditController(FormFactory formFactory, MessagesApi messagesApi) {
		this.form = formFactory.form(CommentData.class);
		this.messagesApi = messagesApi;
	}

	public Result editCommentPage(int id, Http.Request request) {
		this.comment = finder.byId(id);
		return ok(views.html.edit.render(this.comment, this.form, request, this.messagesApi.preferred(request)));
	}

	public Result editComment(Http.Request request) {
		Form<CommentData> boundForm = form.bindFromRequest(request);

		CommentData data = boundForm.get();
		String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		finder.update()
			.set("comment", data.getComment())
			.set("updatedAt", nowDate)
			.where()
				.eq("id", data.getId())
				.update();

		return redirect(routes.HomeController.topPage()).flashing("info", "edited!");
	}
} 