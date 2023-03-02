package com.revathi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revathi.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	 @Query(value = "SELECT a FROM User a WHERE a.email=?1 AND a.password = ?2")
	 Optional<User> findByEmailAndPassword(String email , String password);
	 
	 @Query(value = "SELECT a FROM User a WHERE a.mobile_num=?1 ")
	 Optional<User> findByMobNo(long mobileNumber);
}
