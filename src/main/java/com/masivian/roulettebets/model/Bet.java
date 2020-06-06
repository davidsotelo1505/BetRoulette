package com.masivian.roulettebets.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "bet")
public class Bet implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id_bet;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roulette_id")
	private Roulette roulette;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User user;
	private Integer amount;
	private Date date_bet;
	private String color_bet;
	private Integer numbre_bet;
	private Boolean status;
	
	
	public Bet() {
		super();
	}
	
	public Roulette getRoulette() {
		return roulette;
	}

	public void setRoulette(Roulette roulette) {
		this.roulette = roulette;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId_bet() {
		return id_bet;
	}
	public void setId_bet(Integer id_bet) {
		this.id_bet = id_bet;
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
	public String getColor_bet() {
		return color_bet;
	}
	public void setColor_bet(String color_bet) {
		this.color_bet = color_bet;
	}
	public Integer getNumbre_bet() {
		return numbre_bet;
	}
	public void setNumbre_bet(Integer numbre_bet) {
		this.numbre_bet = numbre_bet;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
	
	
	
	

}
