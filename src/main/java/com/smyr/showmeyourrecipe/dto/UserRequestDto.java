package com.smyr.showmeyourrecipe.dto;

import com.smyr.showmeyourrecipe.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
	private String username;
	private String password;
	private String email;
	private String introduce;
	private String role;
}
