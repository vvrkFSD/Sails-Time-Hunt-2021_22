package com.sailssoft.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ResetPasswordRequest {

	private final String password;
	private final String confirmPassword; 
}

