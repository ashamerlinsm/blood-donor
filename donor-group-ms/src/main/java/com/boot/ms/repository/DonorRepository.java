package com.boot.ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boot.ms.entity.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer>{

	public List<Donor> findAllByBloodGroupId(int bloodGroupId);

}
