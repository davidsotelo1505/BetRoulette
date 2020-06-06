package com.masivian.roulettebets.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.roulettebets.GeneralResponse;
import com.masivian.roulettebets.model.Roulette;
import com.masivian.roulettebets.service.RouletteService;

@RestController
@RequestMapping("/roulette")
public class RouletteController {
	
	final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RouletteService rouletteService;
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GeneralResponse<Roulette>> saveRoulette(Roulette roulette){
		HttpStatus status = HttpStatus.OK;
		GeneralResponse<Roulette> response = new GeneralResponse<>();
		try {
			rouletteService.save(roulette);
			response.setData(roulette);
			response.setSuccess(true);
			
		} catch (Exception e) {
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<GeneralResponse<Roulette>>(response, status);
		
	}

}
