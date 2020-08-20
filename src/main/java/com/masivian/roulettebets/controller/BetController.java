package com.masivian.roulettebets.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.roulettebets.ApiError;
import com.masivian.roulettebets.GeneralResponse;
import com.masivian.roulettebets.model.Bet;
import com.masivian.roulettebets.model.Roulette;
import com.masivian.roulettebets.model.User;
import com.masivian.roulettebets.service.BetService;

@RestController
@RequestMapping("/bet")
public class BetController {
	
	final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BetService betService;
	
	ApiError apiError = null;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<GeneralResponse<Bet>> saveBet(@RequestBody Bet bet, @RequestHeader("id") Long id){
		HttpStatus status = HttpStatus.OK;
		GeneralResponse<Bet> response = new GeneralResponse<>();
		try {
			bet.setUser(new User(id));
			Bet betaux= betService.getfindById(betService.save(bet).getId());
			response.setData(betaux);			
			response.setSuccess(true);
			
		} catch (Exception e) {
			response.setSuccess(false);
			apiError = new ApiError();
			apiError.setMessageUser(e.getMessage().toString());	
			response.setApiError(apiError);
			status = HttpStatus.BAD_REQUEST;
			
		}
		
		return new ResponseEntity<GeneralResponse<Bet>>(response, status);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GeneralResponse<List<Bet>>> findAll(){
		HttpStatus status = HttpStatus.OK;
		GeneralResponse<List<Bet>> response = new GeneralResponse<>();
		try {
			response.setData(betService.findAll());
			response.setSuccess(true);
			
		} catch (Exception e) {
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<GeneralResponse<List<Bet>>>(response, status);
		
	}
	
	

}
