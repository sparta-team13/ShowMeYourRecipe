package com.smyr.showmeyourrecipe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class User {
	@Id
	private long id;
	private String userName;
	private String password;
	private String email;
	private String introduce;
}
