package com.banking.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.banking.exception.InvalidCredentialException;
import com.banking.banking.exception.UserNotFoundException;
import com.banking.banking.model.OrderModel;
import com.banking.banking.model.UserEntity;
import com.banking.banking.model.UserLogin;
import com.banking.banking.service.UserService;

@RestController
@RequestMapping("/")

public class UserController {
	@Autowired
	UserService service;
	
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody UserLogin user) throws UserNotFoundException, InvalidCredentialException{
		UserEntity userDetails = service.findUser(user.uname,user.pwd);
		return ResponseEntity.ok(userDetails.uid);
	}

	
	@GetMapping("balance")
	public ResponseEntity<Float> balance(@RequestParam("uid")  String uid) throws  InvalidCredentialException{
		Float walletBalance = service.getBalanceByUid(uid);
		return ResponseEntity.ok(walletBalance);
	}
	
	@GetMapping("balanceByuid")
	public ResponseEntity<Float> walletbalance(@RequestHeader("uid") String uid) throws  InvalidCredentialException{
		Float walletBalance  = service.getBalanceByUid(uid);
		return ResponseEntity.ok(walletBalance);
	}
	
	@PostMapping("placeOrder")
	public ResponseEntity<String> placeOrder(@RequestHeader("uid") String uid , @RequestBody OrderModel order) throws  InvalidCredentialException{
		if(service.placeOrder(uid,order)) {
			return ResponseEntity.ok("Order Placed Successfully");
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("NOT ENOUGH BALANCE TO ORDER");
	}
	
}
