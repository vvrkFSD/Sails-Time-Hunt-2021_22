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
public class Client {
	
	@Id
	@SequenceGenerator(
			name="client_sequence",
			sequenceName="client_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator = "client_sequence"
	)
	@Column(name="client_id")
	private Long id;
	@Column(name="client_name")
	private String name;
	private String location;

}
