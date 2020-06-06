package com.masivian.roulettebets.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.masivian.roulettebets.model.Bet;

public interface BetService {
	
	Bet save(Bet bet) throws ServiceException;
	
	List <Bet> findAll() throws ServiceException;
	
	

}
