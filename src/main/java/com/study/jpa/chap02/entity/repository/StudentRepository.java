package com.study.jpa.chap02.entity.repository;

import com.study.jpa.chap02.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

}
