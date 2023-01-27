package com.boot.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.ms.entity.Donor;
import com.boot.ms.service.DonorService;

@RestController
@RequestMapping("/donors")
public class DonorController {
	
	@Autowired
	DonorService service;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Donor>> getAllDonors() {
		return new ResponseEntity<List<Donor>>(service.getAllDonors(), HttpStatus.OK);
	}
	
	@GetMapping("/getDonor/{id}")
	public ResponseEntity<?>getDonor(@PathVariable int id) {
		Donor donor = service.getDonor(id);
		ResponseEntity<?> responseEntity = null;
		
		if(donor == null) {
			responseEntity = new ResponseEntity<String>("No donor present with given id" +id, HttpStatus.NOT_FOUND);
		}
		else {
			responseEntity = new ResponseEntity<Donor>(donor, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/getDonors/{bloodGroupId}")
	public ResponseEntity<?> getDonors(@PathVariable int bloodGroupId) {
		List<Donor> list = service.getDonors(bloodGroupId);
		ResponseEntity<?> responseEntity = null;
		if(list.isEmpty()) {
			responseEntity = new ResponseEntity<String>("No donor present with given blood group id"+bloodGroupId, HttpStatus.OK);
		}
		else {
			responseEntity = new ResponseEntity<List<Donor>>(list, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/addDonor")
	public ResponseEntity<Donor> addDonor(@RequestBody Donor donor) {
		Donor donorData = service.addDonor(donor);
		return new ResponseEntity<Donor>(donorData, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteDonor/{id}")
	public List<Donor> deleteDonor(@PathVariable int id) {
		List<Donor> list = service.deleteDonor(id);
		ResponseEntity<?> responseEntity = null;
		if(list == null) {
			responseEntity = new ResponseEntity<String>("No donor present with the given id" +id, HttpStatus.OK);
		}
		return list;
	}
	
	@PutMapping("/updateDonor")
	public Donor updateDonor(@RequestBody Donor donor) {
		return service.updateDonor(donor);
	}
}
