package com.masivian.roulettebets.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.roulettebets.ApiError;
import com.masivian.roulettebets.model.Roulette;
import com.masivian.roulettebets.repository.RouletteRepository;
import com.masivian.roulettebets.ServiceException;

@Service(value = "rouletteService")
public class RouletteServiceImpl implements RouletteService {

	final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RouletteRepository rouletteRepository;

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

}
