package com.smyr.showmeyourrecipe.controller;

import com.smyr.showmeyourrecipe.dto.UserRequestDto;
import com.smyr.showmeyourrecipe.dto.UserResponseDto;
import com.smyr.showmeyourrecipe.jwt.JwtUtil;
import com.smyr.showmeyourrecipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api" )
public class UserController {
	private final UserService userService;
	private final JwtUtil jwtUtil;

	@Autowired
	public UserController( UserService userService, JwtUtil jwtUtil ) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping( "/auth/signup" )
	public ResponseEntity< String > signup( UserRequestDto userRequestDto ) {
		this.userService.signup( userRequestDto );

		return ResponseEntity.ok( "signup success" );
	}

	@GetMapping( "/user/{userId}" )
	public ResponseEntity< UserResponseDto > getUser( @PathVariable long userId ) {
		var userResponseDto = this.userService.getUser( userId );

		return ResponseEntity.ok( userResponseDto );
	}

	@PatchMapping( "/user" )
	public ResponseEntity< String > updateProfile( UserRequestDto userRequestDto, @CookieValue( JwtUtil.AUTHORIZATION_HEADER ) String tokenValue ) {

		var userId = jwtUtil.getUserId( tokenValue );

		userService.updateUser( userId, userRequestDto );

		return ResponseEntity.ok( "update success" );
	}

}
