package com.tcs.trainingAssignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.trainingAssignment.entity.Cart;
import com.tcs.trainingAssignment.service.KartService;

@RestController
public class kartController {

	@Autowired
	private KartService kartService;
	
	@PutMapping("/karts/{customerId}/{productId}") //update the cart increment the quantity by 1
	public ResponseEntity<String> updateKartItemQuantity(@PathVariable Long customerId, @PathVariable Long productId, @RequestParam Integer quantity ) {
		boolean result = kartService.updateKartItemQuantity(customerId, productId,quantity);
		if(result)
			return ResponseEntity.ok().body("updated Succesfully");
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Updation failed!!");
		
	}
	
		
	@DeleteMapping("/karts/{customerId}/{productId}")//delete the cart item by customer id and product id
	public ResponseEntity<String> deleteKartItem(@PathVariable Long customerId, @PathVariable Long productId ) {
		boolean result = kartService.removeProductFromKart(customerId, productId);
		if(result)
			return ResponseEntity.ok().body("deleted Succesfully");
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("deletion failed!!");
		
	}
	@GetMapping("/karts/{customerId}")// get all the items by customerId from cart
	public ResponseEntity<Optional<Cart>> getAllKartItemsByCustomerId(@PathVariable Long customerId) {
		Optional<Cart> result = kartService.getAllKartItemsByCustomerId(customerId);
		if(!result.isEmpty()) return ResponseEntity.ok().body(result);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

	}
	@DeleteMapping("/karts/{customerId}")// delete all the items by customer id from cart
	public ResponseEntity<String> clearCart(@PathVariable Long customerId) {
		boolean result = kartService.clearCart(customerId);
		if(result)
			return ResponseEntity.ok().body("deleted Succesfully");
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("deletion failed!!");
	}
	
	@PostMapping("/karts/{customerId}/{productId}")
	ResponseEntity<String> addProductToCart(@PathVariable Long customerId, @PathVariable Long productId) { 
		boolean result = kartService.addProductToCart(customerId, productId);
		if(result)
			return ResponseEntity.ok().body("Added Sccessfully");
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product cannot be added!!");
		
	}
	
//	@PostMapping("/karts/orders/{customerId}")
//	ResponseEntity<String> placeOrder(@PathVariable Long customerId) {
//		boolean result = kartService.buyingProducts(customerId);
//		if(result)
//			return ResponseEntity.ok().body("Order Placed Sccessfully");
//		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order  cannot be placed!!");
//	}
	}
