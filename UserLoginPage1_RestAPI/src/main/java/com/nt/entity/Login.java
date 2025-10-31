package com.nt.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="logins")
@Data
public class Login {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Lid;
	private String username;
	private String password;
@OneToOne(cascade=CascadeType.ALL)//save both  entity automatically 
//@JoinColumn(name="user_id")//Tell foreign key in user
	private User user;
}
