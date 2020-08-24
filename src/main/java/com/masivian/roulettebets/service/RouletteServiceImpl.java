package com.masivian.roulettebets.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.roulettebets.ApiError;
import com.masivian.roulettebets.model.Bet;
import com.masivian.roulettebets.model.Roulette;
import com.masivian.roulettebets.repository.BetRepository;
import com.masivian.roulettebets.repository.RouletteRepository;
import com.masivian.roulettebets.ServiceException;

@Service(value = "rouletteService")
public class RouletteServiceImpl implements RouletteService {
	final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RouletteRepository rouletteRepository;
	@Autowired
	private BetRepository betRepository;

	@Override
	public Roulette save(Roulette roulette) throws ServiceException {
		log.info("Init save roulette");
		try {
			roulette.setStatus("close");
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
		rouletteList = rouletteRepository.findAll();

		return rouletteList;
	}

	@Override
	public Roulette update(Long id) throws ServiceException {
		log.info("Init update roulette");
		Roulette findToUpdate = rouletteRepository.getFindById(id);
		try {
			if (null == findToUpdate) {
				throw new ServiceException(new ApiError("No se encuentra id", "Id not Found", "1"));
			} else {
				if (findToUpdate.getStatus().equals("close")) {
					findToUpdate.setStatus("open");
					findToUpdate
							.setUpdate_date(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
					rouletteRepository.save(findToUpdate);

					return findToUpdate;
				} else {
					throw new ServiceException(new ApiError("Roulette is open", "Roulette is open", "2"));
				}
			}
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("Failed to update roulette");
		}
	}

	@Override
	public Roulette close(Long id) throws ServiceException {
		log.info("Init update roulette");
		Roulette findToUpdate = rouletteRepository.getFindById(id);
		Random randNumber = new Random();
		try {
			if (null == findToUpdate) {
				throw new ServiceException("Roulette not found");
			}
			if (findToUpdate.getStatus().equals("open")) {
				findToUpdate.setStatus("close");
				findToUpdate.setUpdate_date(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
				findToUpdate.setNumber_win(randNumber.nextInt((36 - 0) + 1) + 0);
				if (findToUpdate.getNumber_win() % 2 == 0) {
					findToUpdate.setColor_win("rojo");
				} else {
					findToUpdate.setColor_win("negro");
				}
				findToUpdate = findBetWin(findToUpdate);
				rouletteRepository.save(findToUpdate);

			} else {
				throw new ServiceException("Roulette is already close");
			}

			return findToUpdate;
		} catch (Exception e) {
			if (e instanceof ServiceException) {
				throw (ServiceException) e;
			}
			throw new ServiceException("The roulette no was update");
		}
	}

	@Override
	public Roulette findById(Long id) throws ServiceException {
		Roulette findById = rouletteRepository.getFindById(id);
		try {
			if (null == findById) {
				throw new ServiceException("Roulette not found");
			}
		} catch (Exception e) {
			if (e instanceof ServiceException) {
				throw (ServiceException) e;
			}
			throw new ServiceException("Error to get Roulette");
		}

		return findById;
	}

	private Roulette findBetWin(Roulette roulette) throws ServiceException {
		List<Bet> listBets = roulette.getBets();
		for (int i = 0; i < listBets.size(); i++) {
			if (null != listBets.get(i).getNumber_bet_user() && null != listBets.get(i).getColor_bet_user()) {
				if (roulette.getNumber_win() == listBets.get(i).getNumber_bet_user()) {
					listBets.get(i).setStatus("WIN");
				} else {
					listBets.get(i).setStatus("LOST");
				}
			} else if (null!=listBets.get(i).getNumber_bet_user()) {
				if(roulette.getNumber_win() ==listBets.get(i).getNumber_bet_user()) {
					listBets.get(i).setStatus("WIN WITH NUMBER");
				}else {
					listBets.get(i).setStatus("LOST");
				}
			} else if (null!=listBets.get(i).getColor_bet_user()) {
				if(roulette.getColor_win().equals(listBets.get(i).getColor_bet_user())) {
					listBets.get(i).setStatus("WIN WITH COLOR");
				}else {
					listBets.get(i).setStatus("LOST");
				}
			} else {
				throw new ServiceException("Rrror when validating winner");
			}
		}
		roulette.setBets(listBets);
		rouletteRepository.save(roulette);

		return roulette;
	}
}