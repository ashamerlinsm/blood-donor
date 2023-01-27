package com.boot.ms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.ms.entity.Donor;
import com.boot.ms.repository.DonorRepository;

@Service
public class DonorService {
	
	@Autowired
	DonorRepository repository;

	public List<Donor> getAllDonors() {
		return repository.findAll();
	}
	
	public Donor getDonor(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Donor> getDonors(int bloodGroupId) {
		return repository.findAllByBloodGroupId(bloodGroupId);
	}
	
	public Donor addDonor(Donor donor) {
		return repository.save(donor);
	}
	
	public List<Donor> deleteDonor(int id) {
		List<Donor> list = null;
		Optional<Donor> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);
			list = getAllDonors();
		}
		else {
			list = null;
		}
		return list;
	}
	
	public Donor updateDonor(Donor donor) {
		Donor donorData = repository.findById(donor.getId()).get();
		donorData.setName(donor.getName());
		return repository.save(donorData);
	}
}
