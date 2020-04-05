package it.objectmethod.loobia.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import it.objectmethod.loobia.dto.UserDto;
import it.objectmethod.loobia.entity.User;
//import it.objectmethod.loobia.mapper.UserMapper;
import it.objectmethod.loobia.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepo;

//	@Autowired
//	private UserMapper userMapper;

	public User findByUsernameAndPassword(String email, String password) {

		User user = userRepo.findByEmailAndPassword(email, password);
		//UserDto userDto = userMapper.toDto(user);
		//return userDto;
		return user;
	}
	

}
