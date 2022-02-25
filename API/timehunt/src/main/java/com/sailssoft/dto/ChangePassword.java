package com.sailssoft.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ChangePassword {

	private final String oldPassword;
	private final String newPassword;
	private final String email;
}
