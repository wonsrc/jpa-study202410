package com.study.jpa.chap02.entity.repository;

import ch.qos.logback.core.testUtil.TeeOutputStream;
import com.study.jpa.chap02.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

//    @BeforeEach
    @Test
    void insertData() {
        Student s1 = Student.builder()
                .name("쿠로미")
                .city("청양군")
                .major("경제학")
                .build();
        Student s2 = Student.builder()
                .name("춘식이")
                .city("서울시")
                .major("컴퓨터공학")
                .build();
        Student s3 = Student.builder()
                .name("어피치")
                .city("제주도")
                .major("화학공학")
                .build();
        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
    }

    @Test
    @DisplayName("이름이 춘식이인 학생의 모든 정보를 조회한다.")
    void findByNameTest() {
        // given
        String name = "춘식이";


        // when
        List<Student> list = studentRepository.findByName(name);

        // then
        assertEquals(1, list.size());
        System.out.println("\n\n\n\n\n");
        System.out.println(list.get(0));
    }

    @Test
    @DisplayName("여러가지 조건을 쿼리메서드로 검색")
    void queryMethodTest() {
        // given
        String city = "제주도";
        String major = "공학";

        // when

        List<Student> students = studentRepository.findByMajorEndingWith(major);

        // then

        System.out.println("\n\n\n\n\n");
        System.out.println(students.get(0));
    }

    @Test
    @DisplayName("도시 또는 이름으로 학생 조회")
    void nativeSQLTest() {
        // given
        String name = "춘식이";
        String city = "제주도";
        // when
        List<Student> students = studentRepository.getStudentByNameOrCity(name, city);

        // then
        System.out.println("\n\n\n\n\n");
        students.forEach(System.out::println);
        System.out.println("\n\n\n\n\n");
    }

    @Test
    @DisplayName("")
    void jpqlTest() {
        // given
        String name = "춘";

        // when
        List<Student> students = studentRepository.searchByNameWithJPQL(name);

        // then
        System.out.println("\n\n\n\n\n");
        students.forEach(System.out::println);
        System.out.println("\n\n\n\n\n");
    }

    @Test
    @DisplayName("JPQL로 삭제하기")
    void deleteJPQLTest() {
        // given
        String name = "어피치";
        String city = "제주도";
        // when
        studentRepository.deleteByNameAndCityWithJPQL(name, city);

        // then
        assertEquals(0, studentRepository.findByName(name).size());
    }
}