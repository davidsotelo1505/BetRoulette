package com.masivian.roulettebets.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.masivian.roulettebets.model.Roulette;

public interface RouletteService {
	
	Roulette save(Roulette roulette) throws ServiceException;
	
	List<Roulette> findAll() throws ServiceException;
	
	

}
