package com.masivian.roulettebets.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.roulettebets.ServiceException;
import com.masivian.roulettebets.model.Bet;
import com.masivian.roulettebets.repository.BetRepository;

@Service(value = "betService")
public class BetServiceImpl implements BetService{
	
	final Logger log = LoggerFactory.getLogger(this.getClass());
	Random randNumber = new Random();
	String[] strings = {"negro", "rojo"};
	@Autowired
	private BetRepository betRepository;

	@Override
	public Bet save(Bet bet) throws ServiceException {
		log.info("Init save bet");
		try {
			bet.setNumber_bet(randNumber.nextInt((36 - 0) + 1) + 0);
			String random = (strings[new Random().nextInt(strings.length)]);
			bet.setColor_bet(random);
			bet.setStatus("active");
			betRepository.save(bet);
		} catch (Exception e) {
			throw new ServiceException("Failed to save bet");
		}
		
		return bet;
	}

	@Override
	public List<Bet> findAll() throws ServiceException {
		
		return betRepository.findAll();
	}

	@Override
	public Bet update(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bet getfindById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
