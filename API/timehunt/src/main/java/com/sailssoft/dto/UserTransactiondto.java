package com.sailssoft.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserTransactiondto {

	private String projectName;
	private String taskName;
	private String taskDate;
	private String description;
	private String timeSpentInHour;
	private String timeSpentInMinute;
	private String email;
	

}
