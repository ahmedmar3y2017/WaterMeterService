package com.devox.app.model.Entities;

import com.devox.app.model.security.Authority;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reading_cost database table.
 * 
 */
@Entity
@Table(name="reading_cost")
public class ReadingCost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;

	@Column(nullable=false)
	private double cost;

	@Column(nullable=false, length=255)
	private String type;

	//bi-directional many-to-one association to Authority
	@ManyToOne
	@JoinColumn(name="authorityid", nullable=false)
	private Authority authority;

	public ReadingCost() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Authority getAuthority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

}