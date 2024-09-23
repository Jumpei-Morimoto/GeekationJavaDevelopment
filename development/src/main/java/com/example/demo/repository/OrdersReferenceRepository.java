package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OrdersReference;

@Repository
public interface OrdersReferenceRepository extends JpaRepository<OrdersReference, Long>{

  List<OrdersReference> findByStoreId(Long long1);

}
