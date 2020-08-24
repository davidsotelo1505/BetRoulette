package com.masivian.roulettebets.service;


import java.util.List;

import com.masivian.roulettebets.ServiceException;

import com.masivian.roulettebets.model.Roulette;

public interface RouletteService {
	Roulette save(Roulette roulette) throws ServiceException;
	List<Roulette> findAll() throws ServiceException;
	Roulette update(Long id) throws ServiceException;
	Roulette close(Long id) throws ServiceException;
	Roulette findById(Long id) throws ServiceException;
}
