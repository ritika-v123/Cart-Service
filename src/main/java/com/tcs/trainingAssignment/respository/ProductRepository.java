package com.tcs.trainingAssignment.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.trainingAssignment.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
