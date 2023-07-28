package com.clayfin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.services.CsvFileSplitter;
import com.clayfin.services.EmployeeCsvGenerator;

@RestController
public class CSVfileController {

	
	EmployeeCsvGenerator csvGenerator = new EmployeeCsvGenerator();
	CsvFileSplitter csvFileSplitter = new CsvFileSplitter();
	
	@GetMapping("/create")
	public ResponseEntity<String> createCSVfile () {
		return new ResponseEntity<>(csvGenerator.csvCreator(),HttpStatus.CREATED);
	}
	
	@GetMapping("/split")
	public ResponseEntity<String> splitCSVfile () {
		return new ResponseEntity<>(csvFileSplitter.splitter(),HttpStatus.CREATED);
	}
}
