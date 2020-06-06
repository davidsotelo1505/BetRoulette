package com.masivian.roulettebets.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.roulettebets.model.Roulette;
import com.masivian.roulettebets.repository.RouletteRepository;

@Service(value = "userService")
public class RouletteServiceImpl implements RouletteService {
	
	final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RouletteRepository rouletteRepository;

	@Override
	public Roulette save(Roulette roulette) throws ServiceException {
		log.info("Init save roulette");
		try {
			roulette.setStatus("open");
			rouletteRepository.save(roulette);
		} catch (Exception e) {
			throw new ServiceException("Failed to save roulette");
		}
		
		return roulette;
	}

	@Override
	public List<Roulette> findAll() throws ServiceException {
		log.info("List of roulette");
		List<Roulette> rouletteList = new ArrayList<>();
		
		return rouletteList;
	}
	
	
	
	

}
