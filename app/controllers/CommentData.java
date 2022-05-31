package controllers;

public class CommentData {
	private int id;
	private int userId;
	private String name;
	private String title;
	private String comment;

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserId() {
		return this.userId;
	}

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