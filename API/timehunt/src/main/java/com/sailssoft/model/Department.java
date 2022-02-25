package com.sailssoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	@Id
	@SequenceGenerator(
			name="department_sequence",
			sequenceName="department_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator = "department_sequence"
	)
	@Column(name="department_id")
	private Long id;
	private String name;
	private String deptCode;

}
