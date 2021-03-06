package com.sailssoft.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import com.sailssoft.dto.AppUserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
	
	@Id
	@SequenceGenerator(
			name="app_user_sequence",
			sequenceName="app_user_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator = "app_user_sequence"
	)
	private Long id;
	@Column(name="Date_Of_Birth")
	private Date DOB;
	@Column(name="gender")
	private String gender;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	public String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
	
	
	
	public AppUser(Date DOB, String gender, String email, String password, AppUserRole appUserRole) {
		super();
		this.DOB = DOB;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}
	
	@ManyToOne
	@JoinColumn(
			nullable =true,
			name="project_id"
	)
	private Project project;


}
