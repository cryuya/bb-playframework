package controllers;

import models.Comments;

import play.mvc.*;
import play.i18n.MessagesApi;
import io.ebean.Finder;
import com.google.common.collect.Lists;
import java.util.List;
import javax.inject.Inject;

import static play.libs.Scala.asScala;

@Security.Authenticated(Secured.class)
public class SearchController extends Controller {
	private Finder<Integer, Comments> finder = new Finder<>(Comments.class);
	private MessagesApi messagesApi;
	private List<Comments> searchedComments = Lists.newArrayList();

	@Inject
	public SearchController(MessagesApi messagesApi) {
		this.messagesApi = messagesApi;
	}

	public Result searchCommentsPage(Http.Request request) {
		String word = request.getQueryString("word");
		if(word != "") {
			this.searchedComments = 
				finder.query()
				.where()
					.or()
						.like("title", "%" + word + "%")
						.like("comment", "%" + word + "%")
				.findList();
		}

		return ok(views.html.search.render(asScala(this.searchedComments), request, this.messagesApi.preferred(request)));
	}
} 