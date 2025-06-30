package com.tcs.trainingAssignment.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.trainingAssignment.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
