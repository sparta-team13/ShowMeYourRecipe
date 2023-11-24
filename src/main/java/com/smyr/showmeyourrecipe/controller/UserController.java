package com.smyr.showmeyourrecipe.controller;

import com.smyr.showmeyourrecipe.dto.user.UserRequestDto;

import com.smyr.showmeyourrecipe.etc.response.ApiResponse;

import com.smyr.showmeyourrecipe.security.UserDetailsImpl;
import com.smyr.showmeyourrecipe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api" )
public class UserController {
	private final UserService userService;

	@PostMapping( "/auth/signup" )
	public @ResponseBody ResponseEntity< ApiResponse > signup( @RequestBody UserRequestDto userRequestDto ) {
		this.userService.signup( userRequestDto );

		return ResponseEntity.ok( ApiResponse.ok( "singup success" ) );
	}

	@GetMapping( "/users/{userId}" )
	public ResponseEntity< ApiResponse > getUser( @PathVariable long userId ) {

		var userResponseDto = this.userService.getUser( userId );

		return ResponseEntity.ok( ApiResponse.ok( userResponseDto ) );
	}

	@PatchMapping( "/users" )
	public ResponseEntity< ApiResponse > updateProfile( @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, UserRequestDto userRequestDto ) {

		var userId = userDetailsImpl.getUser().getId();

		userService.updateUser( userId, userRequestDto );

		return ResponseEntity.ok( ApiResponse.ok( "update success" ) );
	}

}
