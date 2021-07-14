package com.hotcourses.studentprocess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotcourses.studentprocess.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
