package com.micro.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
