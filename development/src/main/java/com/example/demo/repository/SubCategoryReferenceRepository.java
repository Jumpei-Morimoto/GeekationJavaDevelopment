package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Sub_Category_reference;


@Repository
public interface SubCategoryReferenceRepository extends JpaRepository<Sub_Category_reference, Long>{
	
}
