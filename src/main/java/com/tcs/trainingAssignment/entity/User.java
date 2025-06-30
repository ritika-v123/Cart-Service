package com.tcs.trainingAssignment.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	@Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
	private String name;
	@Column(nullable = false, unique= true)
    @NotBlank(message = "Email is mandatory")
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
    @NotBlank(message = "Phone is mandatory")
	private String phone;
	@Column(nullable = false)
    @NotBlank(message = "Address is mandatory")
	private String address;
	@Column(nullable = false)
    @NotNull(message = "Pincode is mandatory")
	private Integer pincode;
	@Column(nullable = false)
	private String role="ROLE_USER";
	@CreationTimestamp
	private LocalDateTime created_Date;
	
	@ManyToMany
    private List<Product> wishlist = new ArrayList<>();
	
	
}
