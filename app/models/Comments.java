package models;

import play.data.validation.Constraints;
import io.ebean.Model;
import io.ebean.Finder;
import javax.persistence.Id;
import javax.persistence.Entity;


@Entity
public class Comments extends Model {
	public static Finder<Long, Comments> finder = new Finder<>(Comments.class);

	@Id 
	private Integer id;
	@Constraints.Required 
	private String name;
	@Constraints.Required 
	private String title;
	@Constraints.Required 
	private String comment;
	@Constraints.Required 
	private String createdAt;
	@Constraints.Required 
	private String updatedAt;

	public Integer getId() {
		return this.id;
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

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedAt() {
		return this.createdAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt() {
		return this.updatedAt;
	}
}