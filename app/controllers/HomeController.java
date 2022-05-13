package controllers;

import java.util.List;
import play.mvc.*;

import models.Comments;

public class HomeController extends Controller {
    public Result index() {
		Comments comment1 = new Comments();
		comment1.setName("firstname");
		comment1.setTitle("firsttitle");
		comment1.setComment("tsts");
		comment1.setCreatedAt("2020-01-01 10:10:10");
		comment1.setUpdatedAt("2020-01-01 10:10:10");
		comment1.save();

		List<Comments> comments = comment1.finder.all();

		return ok(views.html.index.render(comments));
	}
}
