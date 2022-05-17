package models;

import play.data.validation.Constraints;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Comments extends Model {
	@Id
	public Integer id;
	@Constraints.Required 
	public String name;
	@Constraints.Required 
	public String title;
	@Constraints.Required 
	public String comment;

	public String createdAt;
	public String updatedAt;

	public Comments(String name, String title, String comment, String createdAt, String updatedAt) {
		this.name = name;
		this.title = title;
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}