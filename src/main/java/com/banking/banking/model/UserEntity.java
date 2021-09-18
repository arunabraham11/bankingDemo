package com.banking.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "bs_user_det")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {
	@Id
	public String uname;
	public String pwd;
	@Column(unique = true)
	public String uid;
	public float walletBalance;
	

}
