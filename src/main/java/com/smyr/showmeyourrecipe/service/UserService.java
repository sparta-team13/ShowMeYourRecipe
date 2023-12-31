package com.smyr.showmeyourrecipe.service;

import com.smyr.showmeyourrecipe.dto.user.UserRequestDto;
import com.smyr.showmeyourrecipe.dto.user.UserResponseDto;
import com.smyr.showmeyourrecipe.entity.user.EmailAuth;
import com.smyr.showmeyourrecipe.entity.user.User;
import com.smyr.showmeyourrecipe.entity.user.UserRoleEnum;
import com.smyr.showmeyourrecipe.repository.user.EmailAuthRepository;
import com.smyr.showmeyourrecipe.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final EmailAuthRepository emailAuthRepository;
	private final PasswordEncoder passwordEncoder;


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
	public void signupEmailAuth( String id ) {
		EmailAuth emailAuth = this.emailAuthRepository.findById( id ).orElseThrow(
				()-> new NoSuchElementException( "일치하는 이메일 인증 ID를 찾을 수 없습니다." )
		);

		String username = emailAuth.getUsername();;
		var findUser = userRepository.findByUsername( username );
		if( findUser.isPresent() ){
			throw new DuplicateKeyException( "user name : " + username + " duplicated" );
		}

		User user = new User();
		user.setUsername( username );
		user.setPassword( emailAuth.getPassword() );
		user.setEmail(  emailAuth.getEmail() );
		user.setIntroduce( emailAuth.getIntroduce() );
		user.setRole( UserRoleEnum.USER );

		this.userRepository.save( user );

		this.emailAuthRepository.deleteById( id );
	}
}
