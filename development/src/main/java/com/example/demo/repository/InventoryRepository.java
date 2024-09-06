package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inventory;

@Repository

public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	Optional<Inventory> findByStoreId(Long storeName);

	Optional<Inventory> findByItemIdAndStoreId(Long itemId, Long storeId);
	

	

}
