package com.sailssoft.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserTransaction {
	
	@Id
	@SequenceGenerator(
			name="department_transaction_sequence",
			sequenceName="department_transaction_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator = "department_transaction_sequence"
	)
	@Column(name="department_transaction_id")
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(
			nullable =true,
			name="user_id"
	)
	private AppUser user;
	
	
	private String taskName;
	private String description;
	private String taskDate;
	private String timeSpentInHour;
	private String timeSpentInMinute;

}
