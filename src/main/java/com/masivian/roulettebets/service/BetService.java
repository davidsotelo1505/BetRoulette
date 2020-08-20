package com.masivian.roulettebets.service;

import java.util.List;

import com.masivian.roulettebets.ServiceException;
import com.masivian.roulettebets.model.Bet;

public interface BetService {
	
	Bet save(Bet bet) throws ServiceException;
	
	List <Bet> findAll() throws ServiceException;
	
	Bet update(Long id) throws ServiceException;
	
	Bet getfindById(Long id) throws ServiceException;
	

}
