package controllers;

import models.Comments;

import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.ebean.Finder;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;

import static play.libs.Scala.asScala;

public class HomeController extends Controller {
	private final Finder<Integer, Comments> finder = new Finder<>(Comments.class);
	private final Form<CommentData> form;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private MessagesApi messagesApi;
	private List<Comments> comments;

	@Inject
	public HomeController(FormFactory formFactory, MessagesApi messagesApi) {
		this.form = formFactory.form(CommentData.class);
		this.messagesApi = messagesApi;
		this.comments = com.google.common.collect.Lists.newArrayList(
			finder.all()
		);
	}

	public Result listComments(Http.Request request) {
		return ok(views.html.index.render(asScala(comments), form, request, messagesApi.preferred(request)));
	}

	public Result createComment(Http.Request request) {
		final Form<CommentData> boundForm = form.bindFromRequest(request);

		if (boundForm.hasErrors()) {
			logger.error("errors = {}", boundForm.errors());
			return badRequest(views.html.index.render(asScala(comments), boundForm, request, messagesApi.preferred(request)));
		} else {
			CommentData data = boundForm.get();
			String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			Comments c = new Comments(data.getName(), data.getTitle(), data.getComment(), nowDate, nowDate);
			c.save();
			this.comments = com.google.common.collect.Lists.newArrayList(
				finder.all()
			);
			return redirect(routes.HomeController.listComments())
				.flashing("info", "Comment added!");
		}
	}
}