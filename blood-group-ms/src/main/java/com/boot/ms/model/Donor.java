package com.boot.ms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donor {
	
	private int id;
	private String name;
	private int bloodGroupId;

}
