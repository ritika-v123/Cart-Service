package com.tcs.trainingAssignment.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tcs.trainingAssignment.entity.CartItem;
import com.tcs.trainingAssignment.entity.Product;
import com.tcs.trainingAssignment.entity.User;

public interface CartItemRepository extends JpaRepository<CartItem,Long>{

	Optional<CartItem> findByCartUserAndProduct(Optional<User> user, Optional<Product> product);

	void deleteByCartUserAndProduct(Optional<User> user, Optional<Product> product);

}
