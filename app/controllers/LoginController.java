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

public class LoginController extends Controller {
	private Finder<Integer, Users> finder = new Finder<>(Users.class);
	private Form<UserData> form;
	private MessagesApi messagesApi;

	@Inject
	public LoginController(FormFactory formFactory, MessagesApi messagesApi) {
		this.form = formFactory.form(UserData.class);
		this.messagesApi = messagesApi;
	}

	public Result loginPage(Http.Request request) {	
		return ok(views.html.login.render(this.form, request, this.messagesApi.preferred(request))).withNewSession();
	}
	
	public Result login(Http.Request request) {	
		Form<UserData> boundForm = form.bindFromRequest(request);
		if (boundForm.hasErrors()) {
			return badRequest(views.html.login.render(this.form, request, messagesApi.preferred(request)));
		}

		UserData data = boundForm.get();

		Users searchedUser =
			finder.query()
				.where()
					.and()
						.eq("name", data.getName())
						.eq("password", data.getPassword())
				.findOne()
			;

		if (searchedUser != null) {
			return
				redirect("/")
					.addingToSession(request, "id", String.valueOf(searchedUser.id))
					.addingToSession(request, "name", data.getName())
					.flashing("", "logged in");
		} else {
			return
				redirect("/login")
					.flashing("", "failure");
		}
	}

	public Result logout() {
		return redirect("/login").withNewSession();
	}
} 