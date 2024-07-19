package com.example.demo.controller;

import com.example.demo.constant.ApplicationConstant;
import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

	private final StudentDao dao;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@PostMapping("/register")
	public ResponseEntity<String> registerStudent(@RequestBody Student student) {
		student.setCreationDate(LocalDateTime.now());
		student.setLastUpdatedDate(LocalDateTime.now());
		dao.save(student);
		log.info("Student Details Register Successfully");
		return ResponseEntity.ok("student saved successfully");
	}
	
	@GetMapping("/fetch/{id}")
	public Optional<Student> fetchStudent(@PathVariable("id") Integer id) {
		return dao.findById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateStudentDetails(@PathVariable("id") Integer id,
			@RequestBody Student student) {
		Optional<Student> student2 = dao.findById(id);
		if(student2.isPresent()) {
			dao.save(student);
			student.setLastUpdatedDate(LocalDateTime.now());
			return ResponseEntity.ok("student updated successfully");
		}
		return ResponseEntity.badRequest().body("Student does not exist");
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudentDetails(@PathVariable("id") Integer id){
		dao.deleteById(id);
		return ResponseEntity.ok("student deleted successfully");
	}

	@GetMapping("/generateOtp")
	public String sendMessage() {

		try {
			Random random = new Random();
			int randomNumber = 100000 + random.nextInt(900000);
			kafkaTemplate.send(ApplicationConstant.TOPIC_NAME, randomNumber);
			log.info("OTP : " + randomNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "OTP has been sent successfully";
	}
}
