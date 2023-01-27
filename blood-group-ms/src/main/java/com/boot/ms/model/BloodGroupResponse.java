package com.boot.ms.model;

import java.util.List;

import com.boot.ms.entity.BloodGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodGroupResponse {
	
	private BloodGroup bloodGroup;
	private List<Donor> donor;

}
