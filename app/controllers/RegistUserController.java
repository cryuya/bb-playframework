package controllers;

import io.ebean.Finder;
import models.Users;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.libs.Scala.asScala;

public class RegistUserController extends Controller {
	private Finder<Integer, Users> finder = new Finder<>(Users.class);
	private Form<UserData> form;
	private MessagesApi messagesApi;
	private List<Users> users = com.google.common.collect.Lists.newArrayList();

	@Inject
	public RegistUserController(FormFactory formFactory, MessagesApi messagesApi) {
		this.form = formFactory.form(UserData.class);
		this.messagesApi = messagesApi;
	}

	public Result registUserPage(Http.Request request) {	
		this.users = finder.all();

		return ok(views.html.registUser.render(asScala(this.users), this.form, request, this.messagesApi.preferred(request)));
	}
	
	public Result registUser(Http.Request request) {	
		Form<UserData> boundForm = form.bindFromRequest(request);

		if (boundForm.hasErrors()) {
			return badRequest(views.html.login.render(this.form, request, messagesApi.preferred(request)));
		}

		UserData data = boundForm.get();

		Users searchedUser =
			finder.query()
				.where()
					.eq("name", data.getName())
				.findOne();

		if (searchedUser == null) {
			Users u = new Users(data.getName(), data.getPassword());
			u.save();

			return
				redirect("/")
					.addingToSession(request, "id", String.valueOf(data.getId()))
					.addingToSession(request, "id", data.getName())
					.flashing("", "registed user");
		} else {
			return
				redirect("/registUser")
					.flashing("", "failure");
		}
	}
}
