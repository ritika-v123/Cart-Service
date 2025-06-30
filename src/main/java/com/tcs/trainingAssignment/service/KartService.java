package com.tcs.trainingAssignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.trainingAssignment.entity.Cart;
import com.tcs.trainingAssignment.entity.CartItem;
import com.tcs.trainingAssignment.entity.OrderItem;
import com.tcs.trainingAssignment.entity.Orders;
import com.tcs.trainingAssignment.entity.Product;
import com.tcs.trainingAssignment.entity.User;
import com.tcs.trainingAssignment.respository.CartItemRepository;
import com.tcs.trainingAssignment.respository.CartRepository;
import com.tcs.trainingAssignment.respository.OrderItemRepository;
import com.tcs.trainingAssignment.respository.OrderRepository;
import com.tcs.trainingAssignment.respository.ProductRepository;
import com.tcs.trainingAssignment.respository.UserRepository;

@Service
public class KartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public boolean addProductToCart(Long customerId, Long productId) {

		Cart cart = new Cart();
		CartItem cartItem = new CartItem();

		Optional<User> user = userRepository.findById(customerId);
		if (user.isEmpty())
			return false;

		Optional<Product> product = productRepository.findById(productId);
		if (product.isEmpty())
			return false;

		Optional<Cart> getCart = cartRepository.findByUser(user);
		if (getCart.isEmpty()) {
			cart.setUser(user.get());
			cartItem.setProduct(product.get());
			cartItem.setCart(cart);
			cart.getCartItems().add(cartItem);
			Cart result = cartRepository.save(cart);

			if (result != null)
				return true;
			return false;
		}
		cartItem.setProduct(product.get());
		cartItem.setCart(getCart.get());
		CartItem result = cartItemRepository.save(cartItem);
		if (result != null)
			return true;
		return false;

	}



	public boolean updateKartItemQuantity(Long customerId, Long productId, Integer quantity) {
		Optional<User> user = userRepository.findById(customerId);
		if (user.isEmpty())
			return false;

		Optional<Product> product = productRepository.findById(productId);
		if (product.isEmpty())
			return false;
	
		Optional<CartItem> getCartItem = cartItemRepository.findByCartUserAndProduct(user, product);
	    if(getCartItem.isEmpty())return false;
		if(getCartItem.get().getQuantity() < 1)return false;
	    getCartItem.get().setQuantity(quantity);
	    
		
	    cartItemRepository.save(getCartItem.get());
		return true;
	}

	public boolean removeProductFromKart(Long customerId, Long productId) {
		Optional<User> user = userRepository.findById(customerId);
		if (user.isEmpty())
			return false;

		Optional<Product> product = productRepository.findById(productId);
		if (product.isEmpty())
			return false;
	
		Optional<CartItem> getCartItem = cartItemRepository.findByCartUserAndProduct(user, product);
	    if(getCartItem.isEmpty())return false;
	    Long id = getCartItem.get().getCartId();
	    cartItemRepository.deleteById(id);
	    return true;

	}

	public Optional<Cart> getAllKartItemsByCustomerId(Long customerId) {
		Optional<User> user = userRepository.findById(customerId);
		if (user.isEmpty())
			return null;

		return cartRepository.findByUser(user);

	}

	public boolean clearCart(Long customerId) {
		
		Optional<User> user = userRepository.findById(customerId);
		if (user.isEmpty())
			return false;
		Optional<Cart> getCart = cartRepository.findByUser(user);
		cartRepository.deleteById(getCart.get().getCartId());
		return true;

	}
	
	public boolean buyProducts(Long customerId) {
		double totalAmount = 0;
		List<OrderItem> orderItems = new ArrayList<>();
		Optional<User> user = userRepository.findById(customerId);
		if (user.isEmpty())
			return false;
		Optional<Cart> getCart = cartRepository.findByUser(user);
		if(getCart.isEmpty())return false;
		Orders order = new Orders();
		
		
		order.setUser(user.get());
		
		for(CartItem c : getCart.get().getCartItems()) {
			totalAmount = c.getProduct().getPrice()*c.getQuantity() + totalAmount;
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(c.getProduct());
			orderItem.setQuantity(c.getQuantity());
			orderItem.setOrders(order);
		
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		order.setTotalAmount(totalAmount);
		
		orderRepository.save(order);
		
		cartRepository.delete(getCart.get());
			
		return true;
	}

}