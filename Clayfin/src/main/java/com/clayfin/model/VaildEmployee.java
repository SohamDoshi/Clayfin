package com.clayfin.model;

import com.opencsv.bean.CsvBindByPosition;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VaildEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@CsvBindByPosition(position = 0)
	private String name;
	@NotEmpty
	@Email
	@CsvBindByPosition(position = 1)
	private String email;
	@NotEmpty
	@CsvBindByPosition(position = 2)
	private String mobileNumber;
	@NotEmpty
	@Pattern(regexp = "[A-Z]{5}\\d{4}[A-Z]{1}")
	@CsvBindByPosition(position = 3)
	private String panNumber;
	private String status;
}
