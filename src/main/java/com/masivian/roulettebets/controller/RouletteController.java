package com.masivian.roulettebets.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.roulettebets.GeneralResponse;
import com.masivian.roulettebets.ServiceException;
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
			
		} catch (ServiceException e) {
			response.setApiError(e.getApiError());
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
		} 
		
		return new ResponseEntity<GeneralResponse<Roulette>>(response, status);
		
	}
	
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ResponseEntity<GeneralResponse<List<Roulette>>> getUserList() {
		HttpStatus status = HttpStatus.OK;
		GeneralResponse<List<Roulette>> response = new GeneralResponse<>();
		try {
			response.setData(rouletteService.findAll());
			response.setSuccess(true);
		} catch (ServiceException e) {
			log.error(e.getApiError().toString());
			response.setSuccess(false);
			response.setApiError(e.getApiError());
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<GeneralResponse<List<Roulette>>>(response, status);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<GeneralResponse<Roulette>> updateRoulette(@PathVariable(name = "id")Long id){
		GeneralResponse<Roulette> response = new GeneralResponse<>();
		log.info(" Init updateRoulette");
		HttpStatus status = HttpStatus.OK;
		try {
			response.setData(rouletteService.update(id));
			response.setSuccess(true);
		} catch (ServiceException e) {
			response.setSuccess(false);
			response.setApiError(e.getApiError());
			status = HttpStatus.BAD_REQUEST;
		} 
		return new ResponseEntity<GeneralResponse<Roulette>>(response, status);
	}
	

	
	
	

}
