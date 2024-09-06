package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Sub_SubCategoryReference;

@Repository
public interface SubSubCategoryReferenceRepository extends JpaRepository<Sub_SubCategoryReference, Long>{

}
