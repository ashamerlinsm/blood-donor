package com.boot.ms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bloodgroup")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodGroup {

	@Id
	@Column(name = "bloodgroup_id")
	private int id;
	@Column(name = "bloodgroup_name")
	private String name;
}
