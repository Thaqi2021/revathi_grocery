package com.revathi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.revathi.service.ProductServiceImpl;
import com.revathi.model.Product;
import com.revathi.model.User;
import com.revathi.service.UserServiceImpl;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
public class GeneralController {
	@Autowired
	UserServiceImpl userimpl;
	
	 @Autowired
	 private ProductServiceImpl proimpl;
	 
	@GetMapping("/products")
    public List<Product> getproducts() {
		System.out.println("im list of product");
        return (List<Product>) proimpl.showProduct();
    }
//	@GetMapping("/users")
//    public List<User> getUsers() {
//		System.out.println("im revathi application......");
//        return (List<User>) userimpl.getAllUser();
//    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
    	System.out.println("i m login in server"+user.getEmail());
    	Optional<User> a=userimpl.checkUser(user.getEmail(),user.getPassword());
    	System.out.println(a.get().getCust_id()+"+++");
    	if(a.isEmpty()) {
    		return (ResponseEntity<?>) ResponseEntity.internalServerError();
    	}else if(user.getEmail().equals("revathiAdmin@gmail.com")&&user.getPassword().equals("revathiSuresh")) {
    		 Map mp = new HashMap();
			 mp.put("status",200);
			 mp.put("msg","adminSuresh");
	         return ResponseEntity.ok(mp);
    	}
    	else {    		
    		return ResponseEntity.ok(a.get());
    	}
    }
    @PostMapping("/signUp")
    public ResponseEntity<?> NewUser(@RequestBody User user) {
    	System.out.println("i m register in server");
    	String a=userimpl.saveUser(user);
    	System.out.println(a+"................");
    	if(a.equals(null)) {
    		return (ResponseEntity<?>) ResponseEntity.internalServerError();
    	}
    	else {
    		
    		return ResponseEntity.ok(user);
    	}
    }
    
    @PostMapping("/home")
    public void addUser(@RequestBody User user) {
    	System.out.println("im working fine......");
    	userimpl.saveUser(user);
    }
}
