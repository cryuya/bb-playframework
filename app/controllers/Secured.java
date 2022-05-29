package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import java.util.Optional;

public class Secured extends Security.Authenticator {
	@Override
	public Optional getUsername(Http.Request req) {
		return req.session().getOptional("id");
	}

	@Override
	public Result onUnauthorized(Http.Request req) {
		return redirect(controllers.routes.LoginController.login()).
			flashing("danger",  "You need to login before access the application.");
	}
}
