package com.boot.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
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
import org.springframework.web.client.RestTemplate;

import com.boot.ms.entity.BloodGroup;
import com.boot.ms.model.BloodGroupResponse;
import com.boot.ms.model.Donor;
import com.boot.ms.service.BloodGroupService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/bloodgroup")
@RibbonClient(name = "DONOR-MS")
public class BloodGroupController {
	
	@Autowired
	BloodGroupService service;
	
	@Autowired
	RestTemplate template;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getAll")
	public ResponseEntity<?> getBloodGroups() {
		//List<Donor> list = template.getForObject("http://DONOR-MS/donors/getAll", List.class);
		return new ResponseEntity<List<BloodGroup>>(service.getBloodGroups(), HttpStatus.OK);
	}
	
	@GetMapping("/getBloodGroup/{id}")
	@HystrixCommand(defaultFallback = "callFallBack")
	public ResponseEntity<?>getBloodGroup(@PathVariable int id) {
		BloodGroup bloodGroup = service.getBloodGroup(id);
		ResponseEntity<?> responseEntity = null;
		
		if(bloodGroup == null) {
			responseEntity = new ResponseEntity<String> ("No blood group present with given id" +id, HttpStatus.OK);
		}
		else {
			responseEntity = new ResponseEntity<BloodGroup>(bloodGroup, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getBloodGroupAndDonors/{id}")
	@HystrixCommand(defaultFallback = "callFallBack")
	public ResponseEntity<?> getBloodGroupAndOrders(@PathVariable int id) {

		BloodGroup bloodGroup = service.getBloodGroup(id);
		ResponseEntity<?> responseEntity = null;

		if (bloodGroup == null) {
			responseEntity = new ResponseEntity<String>("No blood group present with the given id: " +id, HttpStatus.NOT_FOUND);
		} 
		else {
			List<Donor> orderList = template.getForObject("http://localhost:5222/donors/getDonors/" + bloodGroup.getId(), List.class);
			
			BloodGroupResponse response = new BloodGroupResponse();
			response.setBloodGroup(bloodGroup);
			response.setDonor(orderList);
			
			responseEntity = new ResponseEntity<BloodGroupResponse>(response, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/addBloodGroup")
	@HystrixCommand(defaultFallback = "callFallBack")
	public ResponseEntity<?> addBloodGroup(@RequestBody BloodGroup bloodGroup) {
		BloodGroup bloodGroupData = service.addBloodGroup(bloodGroup);
		return new ResponseEntity<BloodGroup>(bloodGroupData, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBloodGroup/{id}")
	public List<?> deleteBloodGroup(@PathVariable int id) {
		List<BloodGroup> list = service.deleteBloodGroup(id);
		ResponseEntity<?> responseEntity = null;
		if(list == null) {
			responseEntity = new ResponseEntity<String>("No blood group present with the given id" +id, HttpStatus.OK);
		}
		return list;
	}
	
	@PutMapping("/updateBloodGroup")
	public BloodGroup updateBloodGroup(@RequestBody BloodGroup bloodGroup) {
		return service.updateBloodGroup(bloodGroup);
	}
	
	public ResponseEntity<?> callFallBack() {
		return new ResponseEntity<String>("Service is down......", HttpStatus.SERVICE_UNAVAILABLE);
	}
}
