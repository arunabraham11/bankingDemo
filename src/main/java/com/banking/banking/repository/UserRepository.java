package com.banking.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.banking.banking.model.UserEntity;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	@Query("SELECT u.walletBalance from UserEntity u where u.uid= ?1")
	Optional<Float> findBalanceByUid(String uid);

	

}
