package com.masivian.roulettebets.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.masivian.roulettebets.model.User;


public interface UserService {
	
	User save(User user) throws ServiceException;
	
	List<User> findAll() throws ServiceException;
	
}
