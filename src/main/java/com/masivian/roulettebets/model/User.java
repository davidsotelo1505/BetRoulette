package com.masivian.roulettebets.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	private Integer user_id;
	private String name;
	private Boolean status;
	private Integer credit;
	
	public User() {
		super();
	}
	public Integer getId_user() {
		return user_id;
	}
	public void setId_user(Integer id_user) {
		this.user_id = id_user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	
	

}
