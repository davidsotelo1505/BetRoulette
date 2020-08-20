package com.masivian.roulettebets.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.roulettebets.ApiError;
import com.masivian.roulettebets.GeneralResponse;
import com.masivian.roulettebets.model.User;
import com.masivian.roulettebets.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	ApiError apiError = null;
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<GeneralResponse<User>> saveUser(@RequestBody User user) {
		HttpStatus status = HttpStatus.OK;
		GeneralResponse<User> response = new GeneralResponse<>();
		try {
			user.setStatus(true);
			response.setData(userService.save(user));
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			apiError = new ApiError();
			apiError.setMessageUser(e.getMessage().toString());
			response.setApiError(apiError);
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<GeneralResponse<User>>(response, status);
	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GeneralResponse<List<User>>> findAll() {
		HttpStatus status = HttpStatus.OK;
		GeneralResponse<List<User>> response = new GeneralResponse<>();
		try {
			response.setData(userService.findAll());
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<GeneralResponse<List<User>>>(response, status);
	}
}