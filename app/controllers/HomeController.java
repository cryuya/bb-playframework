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

public class HomeController extends Controller {
	private Finder<Integer, Comments> finder = new Finder<>(Comments.class);
	private Form<CommentData> form;
	private Form<DeleteCommentData> deleteForm;
	private MessagesApi messagesApi;
	private List<Comments> comments;

	@Inject
	public HomeController(FormFactory formFactory, MessagesApi messagesApi) {
		this.form = formFactory.form(CommentData.class);
		this.deleteForm = formFactory.form(DeleteCommentData.class);
		this.messagesApi = messagesApi;
		this.comments = com.google.common.collect.Lists.newArrayList(
			finder.all()
		);
	}

	public Result listComments(Http.Request request) {
		return ok(views.html.index.render(asScala(comments), form, deleteForm, request, messagesApi.preferred(request)));
	}

	public Result createComment(Http.Request request) {
		Form<CommentData> boundForm = form.bindFromRequest(request);

		CommentData data = boundForm.get();
		String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Comments c = new Comments(data.getName(), data.getTitle(), data.getComment(), nowDate, nowDate);
		c.save();
		this.comments = com.google.common.collect.Lists.newArrayList(
			finder.all()
		);
		return redirect(routes.HomeController.listComments()).flashing("info", "Comment added!");
	}

	public Result deleteComment(Http.Request request) {
		Form<DeleteCommentData> boundForm = deleteForm.bindFromRequest(request);

		DeleteCommentData data = boundForm.get();
		finder.deleteById(data.getId());
		System.out.println(request);
		this.comments = com.google.common.collect.Lists.newArrayList(
			finder.all()
		);
		return redirect(routes.HomeController.listComments()).flashing("info", "delete!");
	}
}