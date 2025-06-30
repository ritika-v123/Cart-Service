package com.tcs.trainingAssignment.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.trainingAssignment.entity.Orders;


public interface OrderRepository extends JpaRepository<Orders,Long>{

}
