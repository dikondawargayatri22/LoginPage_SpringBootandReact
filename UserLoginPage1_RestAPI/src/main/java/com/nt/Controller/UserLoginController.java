package com.nt.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Service.IUserLoginService;
import com.nt.entity.User;

@RestController	//handle Http request and return json response
@CrossOrigin(origins={"http://127.0.0.1:5501","http://localhost:5501"})//run React frontend to backend without error
@RequestMapping("/api/user")//base url for register and login api
public class UserLoginController {
	
	//dependency injection for business logic
	@Autowired
	 private IUserLoginService userLoginService;;
	//dependecy injection for encoding password by registering and verify login
	@Autowired
	private BCryptPasswordEncoder encoder;
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
	    System.out.println("Received User: " + user);
	    String result = userLoginService.registerUser(user);

	    if (result.contains("exists") || result.contains("registered")) {
	        return ResponseEntity.badRequest().body(result);
	    }
	    return ResponseEntity.ok(result);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User user){
		System.out.println("Login request:"+user.getUsername()+","+user.getPassword());
		 User existingUser = userLoginService.findByUsername(user.getUsername());

		if(existingUser ==null) {
		return ResponseEntity.status(401).body("User not found");
	}
	if(!encoder.matches(user.getPassword(),existingUser.getPassword())) {
		return ResponseEntity.status(401).body("Invalid password");	}
	
	return ResponseEntity.ok("Login successful");

//		if (user != null) {
//	        return ResponseEntity.ok("Login successful");  // 200 OK
//	    } else {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found"); // 401
//	    }

}
}