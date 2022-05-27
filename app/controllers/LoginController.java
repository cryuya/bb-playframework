package controllers;

import models.Users;

import play.mvc.*;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import io.ebean.Finder;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static play.libs.Scala.asScala;

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
		UserData data = boundForm.get();

		if (boundForm.hasErrors()) {
			return badRequest(views.html.login.render(this.form, request, messagesApi.preferred(request)));
		} else {
			Users searchedUser = 
				finder.query()
					.where()
						.and()
							.eq("name", data.getName())
							.eq("password", data.getPassword())
					.findOne();

			if (searchedUser != null) {
				return 
					redirect(routes.HomeController.topPage())
						.addingToSession(request, "id", String.valueOf(data.getId()))
						.flashing("success", "ログイン！");
			} else {
				return 
					redirect(routes.LoginController.loginPage())
						.flashing("failure", "間違っています");
			}
		}
	}
} 