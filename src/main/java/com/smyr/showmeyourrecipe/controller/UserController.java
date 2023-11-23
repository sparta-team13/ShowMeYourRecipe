package com.smyr.showmeyourrecipe.controller;

import com.smyr.showmeyourrecipe.dto.UserRequestDto;
import com.smyr.showmeyourrecipe.dto.UserResponseDto;
import com.smyr.showmeyourrecipe.jwt.JwtUtil;
import com.smyr.showmeyourrecipe.security.UserDetailsImpl;
import com.smyr.showmeyourrecipe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api" )
public class UserController {
	private final UserService userService;

	@PostMapping( "/auth/signup" )
	public ResponseEntity< String > signup( UserRequestDto userRequestDto ) {
		this.userService.signup( userRequestDto );

		return ResponseEntity.ok( "signup success" );
	}

	@GetMapping( "/users/{userId}" )
	public ResponseEntity< UserResponseDto > getUser( @PathVariable long userId ) {
		var userResponseDto = this.userService.getUser( userId );

		return ResponseEntity.ok( userResponseDto );
	}

	@PatchMapping( "/user" )
	public ResponseEntity< String > updateProfile( @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, UserRequestDto userRequestDto ) {

		var userId = userDetailsImpl.getUser().getId();

		userService.updateUser( userId, userRequestDto );

		return ResponseEntity.ok( "update success" );
	}

}
