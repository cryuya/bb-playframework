package controllers;

import play.data.validation.Constraints;

public class CommentData {
	@Constraints.Required 
	private String name;
	@Constraints.Required 
	private String title;
	@Constraints.Required 
	private String comment;

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return this.comment;
	}
}