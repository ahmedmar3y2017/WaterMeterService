package com.devox.app.model.Entities;

import com.devox.app.model.security.User;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the district database table.
 * 
 */
@Entity
@Table(name="district")
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;

	@Column(nullable=false, length=255)
	private String name;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="district")
	private List<User> users;

	public District() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setDistrict(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setDistrict(null);

		return user;
	}

}