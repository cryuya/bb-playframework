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
public class EditController extends Controller {
	private Finder<Integer, Comments> commentsFinder = new Finder<>(Comments.class);
	private Form<CommentData> commentsForm;
	private MessagesApi messagesApi;
	private Comments comment;

	@Inject
	public EditController(FormFactory formFactory, MessagesApi messagesApi) {
		this.commentsForm = formFactory.form(CommentData.class);
		this.messagesApi = messagesApi;
	}

	public Result editCommentPage(int id, Http.Request request) {
		this.comment = commentsFinder.byId(id);
		return ok(views.html.edit.render(this.comment, this.commentsForm, request, this.messagesApi.preferred(request)));
	}

	public Result editComment(Http.Request request) {
		Form<CommentData> boundForm = commentsForm.bindFromRequest(request);
		CommentData commentData = boundForm.get();

		String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		commentsFinder.update()
			.set("comment", commentData.getComment())
			.set("updatedAt", nowDate)
			.where()
				.eq("id", commentData.getId())
				.update();

		return redirect("/").flashing("", "edited!");
	}
} 