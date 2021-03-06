package com.hotcourses.studentprocess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotcourses.studentprocess.entity.Student;
import com.hotcourses.studentprocess.exception.RecordNotFoundException;
import com.hotcourses.studentprocess.model.StudentResponse;
import com.hotcourses.studentprocess.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentservice;

	@GetMapping("/details")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> list = studentservice.getAllStudents();
		return new ResponseEntity<List<Student>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<StudentResponse> createStudent(@RequestBody Student student) {
		Long studentId = studentservice.createStudent(student);
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setStudentId(studentId);
		studentResponse.setSuccess(true);
		return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Student student = studentservice.getStudentById(id);
		return new ResponseEntity<Student>(student, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Student> createOrUpdateStudent(Student studentmodel) throws RecordNotFoundException {
		Student update = studentservice.createOrUpdateStudent(studentmodel);
		return new ResponseEntity<Student>(update, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteStudentById(@PathVariable("id") Long id) throws RecordNotFoundException {
		studentservice.deleteStudentById(id);
		return HttpStatus.FORBIDDEN;
	}

}
