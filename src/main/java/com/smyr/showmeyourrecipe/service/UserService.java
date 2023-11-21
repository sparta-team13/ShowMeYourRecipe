package com.smyr.showmeyourrecipe.service;

import com.smyr.showmeyourrecipe.dto.UserResponseDto;
import com.smyr.showmeyourrecipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService( UserRepository userRepository ) {
		this.userRepository = userRepository;
	}

	public UserResponseDto getUser( long userId ) {
		var user = this.userRepository.findById( userId ).orElseThrow(
				()-> new NoSuchElementException( "user " + userId + " not exist." )
		);

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserName( user.getUserName() );
		userResponseDto.setEmail( user.getEmail() );
		userResponseDto.setIntroduce( user.getIntroduce() );

		return userResponseDto;
	}
}
