package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Sub_Category;

@Repository
public interface SubCategoryRepository extends JpaRepository<Sub_Category, Long>{



}
