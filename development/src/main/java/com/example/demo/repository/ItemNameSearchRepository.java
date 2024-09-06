package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Items;

@Repository

public interface ItemNameSearchRepository extends JpaRepository<Items, Long>{

	List<Items> findByItemName(String itemName);
	
	List<Items> findBySubCategoryId(Long subCategoryId);

	List<Items> findByCategoryId(Long categoryId);

	List<Items> findBySubSubCategoryId(Long subSubCatagoryId);

	List<Items> findByItemNameLike(String itemName);
	

}
