package com.boot.ms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="donor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donor {
	
	@Id
	@Column(name="donor_id")
	private int id;
	@Column(name="donor_name")
	private String name;
	@Column(name="bloodgroup_id")
	private int bloodGroupId;
	
}
