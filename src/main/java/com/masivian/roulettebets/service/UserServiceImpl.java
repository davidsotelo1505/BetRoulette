package com.masivian.roulettebets.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.roulettebets.model.Roulette;
import com.masivian.roulettebets.model.User;
import com.masivian.roulettebets.repository.UserRepository;

@Service(value = "userService")
public class UserServiceImpl implements UserService{
	final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepository userRepository;
	@Override
	public User save(User user) throws ServiceException {
		log.info("Init save user");
		try {
			userRepository.save(user);
		} catch (Exception e) {
			throw new ServiceException("Failed to save user");
		}
		
		return user;
	}
	@Override
	public List<User> findAll() throws ServiceException {
		log.info("List of user");
		List<User> userList = new ArrayList<>();
		userList = userRepository.findAll();
		
		return userList;
	}
}