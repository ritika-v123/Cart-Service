package com.tcs.trainingAssignment.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.trainingAssignment.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}
