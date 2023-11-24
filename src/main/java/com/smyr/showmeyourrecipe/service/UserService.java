package com.smyr.showmeyourrecipe.service;

import com.smyr.showmeyourrecipe.dto.UserRequestDto;
import com.smyr.showmeyourrecipe.dto.UserResponseDto;
import com.smyr.showmeyourrecipe.entity.User;
import com.smyr.showmeyourrecipe.entity.UserRoleEnum;
import com.smyr.showmeyourrecipe.jwt.JwtUtil;
import com.smyr.showmeyourrecipe.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService( UserRepository userRepository, PasswordEncoder passwordEncoder ) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public UserResponseDto getUser( long userId ) {
		var user = this.userRepository.findById( userId )
				.orElseThrow( ()-> new NoSuchElementException( "user id : " + userId + " not exist." ) );

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUsername( user.getUsername() );
		userResponseDto.setEmail( user.getEmail() );
		userResponseDto.setIntroduce( user.getIntroduce() );

		return userResponseDto;
	}

	@Transactional
	public void updateUser( long userId, UserRequestDto userRequestDto ) {
		User user = userRepository.findById(userId)
				.orElseThrow( ()-> new NoSuchElementException( "user id : " + userId + " not exist." ) );

		user.setUsername( userRequestDto.getUsername() );
		user.setIntroduce( userRequestDto.getIntroduce() );
		user.setEmail( userRequestDto.getEmail() );
		user.setPassword( passwordEncoder.encode( userRequestDto.getPassword() ) );
	}

	@Transactional
	public void signup( UserRequestDto userRequestDto ) {
		String username = userRequestDto.getUsername();;
		var findUser = userRepository.findByUsername( username );
		if( findUser.isPresent() ){
			throw new DuplicateKeyException( "user name : " + username + " duplicated" );
		}

		User user = new User();
		user.setUsername( username );
		user.setPassword( passwordEncoder.encode( userRequestDto.getPassword() ) );
		user.setEmail( userRequestDto.getEmail() );
		user.setIntroduce( userRequestDto.getIntroduce() );
		user.setRole( UserRoleEnum.USER );

		this.userRepository.save( user );
	}
}
