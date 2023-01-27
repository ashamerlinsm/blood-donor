package com.boot.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.ms.entity.BloodGroup;

@Repository
public interface BloodGroupRepository extends JpaRepository<BloodGroup, Integer>{

}
