package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="student")
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private Integer age;
	@Column(name = "gender")
	private String gender;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "pincode")
	private String pincode;
	@Column(name = "email")
	private String email;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "person_role")
	private String personRole;
	@Column(name = "college")
	private String college;
	@Column(name = "company")
	private String company;
	@Column(name = "designation")
	private String designation;
	@Column(name = "experience")
	private double experience;
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	@Column(name = "last_updated_date")
	private LocalDateTime lastUpdatedDate;
	
}
