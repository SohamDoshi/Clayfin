package com.clayfin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.services.CsvDataTransferService;

@RestController
public class CsvDataTransferController {

	@Autowired
	CsvDataTransferService dataTransferService;
	
	@GetMapping("/transfer")
	public ResponseEntity<String> csvDataTransfer () {
		dataTransferService.transferCsvDataToDatabase();
		return new ResponseEntity<>("Transfered", HttpStatus.OK);
	}
	
	@GetMapping("/vaild_transfer")
	public ResponseEntity<String> csvVaildDataTransfer () {
		dataTransferService.dataTranferToMaster();
		return new ResponseEntity<>("Transfered", HttpStatus.OK);
	}
	
}
