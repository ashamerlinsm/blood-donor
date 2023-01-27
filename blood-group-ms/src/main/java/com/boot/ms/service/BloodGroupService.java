package com.boot.ms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.ms.entity.BloodGroup;
import com.boot.ms.repository.BloodGroupRepository;

@Service
public class BloodGroupService {
	
	@Autowired
	BloodGroupRepository repository;
	
	public List<BloodGroup> getBloodGroups() {
		return repository.findAll();
	}
	
	public BloodGroup getBloodGroup(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public BloodGroup addBloodGroup(BloodGroup bloodGroup) {
		return repository.save(bloodGroup);
	}
	
	public List<BloodGroup> deleteBloodGroup(int id) {
		List<BloodGroup> list = null;
		Optional<BloodGroup> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);
			list = getBloodGroups();
		}
		else {
			list = null;
		}
		return list;
	}
	
	public BloodGroup updateBloodGroup(BloodGroup bloodGroup) {
		BloodGroup bloodGroupData = repository.findById(bloodGroup.getId()).get();
		bloodGroupData.setName(bloodGroup.getName());
		return repository.save(bloodGroupData);
	}

}
