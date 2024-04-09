package com.example.demo.controller;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class StudentController {

	private final StudentDao dao;

	@PostMapping("/register")
	public ResponseEntity<String> registerStudent(@RequestBody Student student) {
		student.setCreationDate(LocalDateTime.now());
		student.setLastUpdatedDate(LocalDateTime.now());
		dao.save(student);
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

}
