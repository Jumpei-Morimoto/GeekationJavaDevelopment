package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Items;

@Repository

public interface CategorySearchRepository extends JpaRepository<Items, Long>{

	List<Items> findByCategoryId(Long categoryId);

}
