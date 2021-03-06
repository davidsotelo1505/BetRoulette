package com.masivian.roulettebets.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity 
@Table(name = "bet")
public class Bet implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long roulette_id;
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	private Integer amount;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
	private Date date_bet;
	private String color_bet_user;
	private Integer number_bet_user;
	private String status;
	public Bet() {
		super();
	}
	@Basic(fetch = FetchType.LAZY)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Date getDate_bet() {
		return date_bet;
	}
	public void setDate_bet(Date date_bet) {
		this.date_bet = date_bet;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getRoulette_id() {
		return roulette_id;
	}
	public void setRoulette_id(Long roulette_id) {
		this.roulette_id = roulette_id;
	}
	public Integer getNumber_bet_user() {
		return number_bet_user;
	}
	public void setNumber_bet_user(Integer number_bet_user) {
		this.number_bet_user = number_bet_user;
	}
	public String getColor_bet_user() {
		return color_bet_user;
	}
	public void setColor_bet_user(String color_bet_user) {
		this.color_bet_user = color_bet_user;
	}
}