package com.sailssoft.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sailssoft.model.AppUser;
import com.sailssoft.model.Project;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserTransactionDTO {

	private AppUser user;
	private Project project;
	private String taskName;
	private String taskDate;
	private String description;
	private String timeSpentInHour;
	private String timeSpentInMinute;
	
	

}
