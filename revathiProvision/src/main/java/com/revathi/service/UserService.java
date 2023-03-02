package com.revathi.service;

import java.util.List;
import java.util.Optional;

import com.revathi.model.User;


public interface UserService {
	public String saveUser(User user);
	List<User> getAllUser();
	Optional<User> checkUser(String email,String password);
	User getUserById(long UserId);
	User updateUser(User user, long id);
    void deleteUser(long id);
//    User getUserbyId(int id);

}
