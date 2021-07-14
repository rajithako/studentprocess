package com.hotcourses.studentprocess.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcourses.studentprocess.entity.Student;
import com.hotcourses.studentprocess.exception.RecordNotFoundException;
import com.hotcourses.studentprocess.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentrepository;

	public List<Student> getAllStudents() {
		List<Student> studentList = studentrepository.findAll();
		if (studentList.size() > 0) {
			return studentList;
		} else {
			return new ArrayList<Student>();
		}
	}

	public Student getStudentById(Long id) throws RecordNotFoundException {
		Optional<Student> student = studentrepository.findById(id);

		if (student.isPresent()) {
			return student.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public Student createOrUpdateStudent(Student student) throws RecordNotFoundException {
		Optional<Student> updatestudent = studentrepository.findById(student.getId());
		if (updatestudent.isPresent()) {
			Student newstudent = updatestudent.get();
			newstudent.setName(student.getName());
			newstudent.setAddress(student.getAddress());
			newstudent.setLocation(student.getLocation());
			newstudent = studentrepository.save(student);
			return newstudent;
		} else {
			student = studentrepository.save(student);
			return student;
		}
	}

	public void deleteStudentById(Long id) throws RecordNotFoundException {
		Optional<Student> removestudent = studentrepository.findById(id);

		if (removestudent.isPresent()) {
			studentrepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public Long createStudent(Student student) {
		Student insertstudent = studentrepository.save(student);
		return insertstudent.getId();

	}

}
