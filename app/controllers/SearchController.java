package controllers;

import models.Comments;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import play.i18n.MessagesApi;
import io.ebean.Finder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static play.libs.Scala.asScala;

public class SearchController extends Controller {
	private Finder<Integer, Comments> finder = new Finder<>(Comments.class);
	private MessagesApi messagesApi;
	private List<Comments> searchedComments = com.google.common.collect.Lists.newArrayList();

	private final Form POST_FORM;

	@Inject
	public SearchController(FormFactory formFactory, MessagesApi messagesApi) {
		this.POST_FORM = formFactory.form();
		this.messagesApi = messagesApi;
	}

	public Result searchPage(Http.Request request){
		return ok(views.html.search.render(asScala(Collections.emptyList()), request, this.messagesApi.preferred(request)));
	}

	public Result searchComments(Http.Request request) {
		Form boundForm = this.POST_FORM.bindFromRequest(request);
		String word = boundForm.field("word").value().get();

		if(!word.isEmpty()){
			this.searchedComments =
					finder.query()
							.where()
							.or()
							.like("title", "%" + word + "%")
							.like("comment", "%" + word + "%")
							.findList();
			return ok(views.html.search.render(asScala(this.searchedComments), request, this.messagesApi.preferred(request)));
		}

		return badRequest(views.html.search.render(asScala(Collections.emptyList()), request, this.messagesApi.preferred(request)));
	}
} 