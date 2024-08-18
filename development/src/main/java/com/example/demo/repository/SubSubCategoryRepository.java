package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Sub_SubCategory;

@Repository
public interface SubSubCategoryRepository extends JpaRepository<Sub_SubCategory, Long>{

}
