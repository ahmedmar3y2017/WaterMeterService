package com.devox.app.model.Entities;

import com.devox.app.model.security.User;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the language database table.
 * 
 */
@Entity
@Table(name="language")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;

	@Column(nullable=false, length=255)
	private String language;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="language")
	private List<User> users;

	public Language() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setLanguage(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setLanguage(null);

		return user;
	}

}