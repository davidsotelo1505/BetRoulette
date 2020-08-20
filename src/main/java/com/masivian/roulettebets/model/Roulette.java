package com.masivian.roulettebets.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity 
@Table(name = "roulette")
public class Roulette implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
	private Date create_date;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="UTC")
	private Date update_date;
	private String status;
	@OneToMany(mappedBy = "id")
	@Column(insertable = false, updatable = false)
    private List<Bet> bets;
	
	
	public List<Bet> getBets() {
		return bets;
	}

	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}

	public Roulette() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

}
