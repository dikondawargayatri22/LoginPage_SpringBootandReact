package com.nt.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users")

@Data

public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long userId;
private String username;
private String password;
private String email;
private Long mobile;

@OneToOne(cascade=CascadeType.ALL)
@JoinColumn(name="lid")
private Login login;
}
