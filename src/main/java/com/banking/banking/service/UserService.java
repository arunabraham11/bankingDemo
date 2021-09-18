package com.banking.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.banking.exception.InvalidCredentialException;
import com.banking.banking.exception.UserNotFoundException;
import com.banking.banking.model.OrderModel;
import com.banking.banking.model.UserEntity;
import com.banking.banking.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public UserEntity findUser(String username, String password)
			throws UserNotFoundException, InvalidCredentialException {
		Optional<UserEntity> user = repository.findById(username);
		if (user.isPresent()) {
			if (password.equals(user.get().pwd)) {
				return user.get();
			}
			throw new InvalidCredentialException("UserName Password do not match");
		} else {
			throw new UserNotFoundException("No employee record exist for given id");
		}
	}

	public Float getBalanceByUid(String uid) throws InvalidCredentialException {
		Optional<Float> balance = repository.findBalanceByUid(uid);
		if (balance.isPresent()) {
			return balance.get();
		}
		throw new InvalidCredentialException("Invalid Uid");
	}
	
	public boolean placeOrder(String uid,OrderModel order) throws InvalidCredentialException {
		
		if(getBalanceByUid(uid) > order.totalCost) {
			return true;
		}
		return false;
	}
}
