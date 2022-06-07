package models;

import play.data.validation.Constraints;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users extends Model {
	@Id
	public Integer id;
	@Constraints.Required 
	public String name;
	@Constraints.Required 
	public String password;

	public Users(String name, String password) {
		this.name = name;
		this.password = password;
	}
}