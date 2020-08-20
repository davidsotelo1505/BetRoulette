package com.masivian.roulettebets.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.roulettebets.model.User;
import com.masivian.roulettebets.repository.UserRepository;

@Service(value = "userService")
public class UserServiceImpl implements UserService{
	
	final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
