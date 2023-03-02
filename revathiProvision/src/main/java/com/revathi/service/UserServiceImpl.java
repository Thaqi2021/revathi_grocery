package com.revathi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revathi.model.User;
import com.revathi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userrep;
	
	public UserServiceImpl(UserRepository userrep) {
		this.userrep=userrep;
	}
	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		Optional<User> o =userrep.findByMobNo(user.getMobile_num());
		System.out.println("user service.........."+o+" "+user.getAddress());

		if(o.isEmpty()) {
			System.out.println("user service..........");
			User check =userrep.save(user);
			return "Done";
		}else {
		return "Already exists";
		}
		
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userrep.findAll();
	}

	@Override
	public Optional<User> checkUser(String email, String password) {
		// TODO Auto-generated method stub
		Optional<User> user =userrep.findByEmailAndPassword(email, password);
		if(user.isPresent()) {
//		updateflag(user.get().getId(),"");
		return user;
		}
		return null;
	}
	@Override
	public User getUserById(long UserId) {
		Optional<User> user =userrep.findById(UserId);
		if(user.isPresent()) {
		return user.get();
		}
		return null;
		// TODO Auto-generated method stub
	}
	@Override
	public User updateUser(User user, long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		userrep.deleteById(id);

		
	}

}
