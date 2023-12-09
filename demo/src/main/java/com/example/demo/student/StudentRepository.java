package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Naming convention for everything that access your database
//Data Access LAYER-This interface is responsible for Data Access Layer
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    //This will transform to:
    //SELECT * FROM student WHERE email=?
    //This annotation can be commented, is not necessary
    //We just add it to be specific what we are doing
    @Query("SELECT s FROM Student s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);


}
