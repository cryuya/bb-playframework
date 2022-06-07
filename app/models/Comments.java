package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comments extends Model {
	@Id
	public Integer id;
	@Constraints.Required 
	public Integer userId;
	@Constraints.Required 
	public String name;
	@Constraints.Required 
	public String title;
	@Constraints.Required 
	public String comment;

	public String createdAt;
	public String updatedAt;

	public Comments(Integer userId, String name, String title, String comment, String createdAt, String updatedAt) {
		this.userId = userId;
		this.name = name;
		this.title = title;
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}