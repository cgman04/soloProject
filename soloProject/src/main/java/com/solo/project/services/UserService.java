package com.solo.project.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.solo.project.models.User;
import com.solo.project.repositories.UserRepository;
import com.solo.project.validators.LoginUser;

@Service
public class UserService {
	
	@Autowired
 private UserRepository userRepo;
 
 public User register(User newUser, BindingResult result) {
 	
 	Optional<User> possibleUser = userRepo.findByEmail(newUser.getEmail());
 	
 	if(possibleUser.isPresent()) {
 		result.rejectValue("email", "Matches", "This email is already registered.");
 	}
     // Reject if password doesn't match confirmation
 	if(!newUser.getPassword().equals(newUser.getConfirm())) {
 	    result.rejectValue("confirm", "Matches", "The passwords do not match!");
 	    return null;
 	}
// 	 Return null if result has errors
 	if(result.hasErrors()) {
 		return null;
 	}

     // Hash and set password, save user to database
 	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
 	newUser.setPassword(hashed);
     return userRepo.save(newUser);
 }
 
 public User login(LoginUser newLog, BindingResult result) {
 	// TO-DO - Reject values:
 	Optional<User> possibleUser = userRepo.findByEmail(newLog.getEmail());
 	
 	if(!possibleUser.isPresent()) {
 		result.rejectValue("email", "MissingAccount", "User does not exist.");
 		return null;
 	}
 	
 	User user = possibleUser.get();
 	if(!BCrypt.checkpw(newLog.getPassword(), user.getPassword())) {
 	    result.rejectValue("password", "Matches", "Invalid Password!");
 	}

 	if(result.hasErrors()) {
 		return null;
 	}
     
     return user;
 }
 
 public User getByEmail(String email) {
 	Optional<User> result = userRepo.findByEmail(email);
 	if(result.isPresent()) {
 		return result.get();
 	}
 	return null;
 }
 
  public User getById(Long id) {
 	 Optional<User> possibleUser = userRepo.findById(id);
 	 if(possibleUser.isPresent()) {
 		 return possibleUser.get();
 	 }
 	 return null;
  }

}
