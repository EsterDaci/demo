package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    //This handles the business logic.
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
      Optional<Student> studentByEmail =
              studentRepository.findStudentByEmail(student.getEmail());
       if(studentByEmail.isPresent()){
           throw new IllegalStateException("Email taken!!!");
       }
       studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) throws Exception {
       boolean exists = studentRepository.existsById(studentId);
       if(!exists){
           throw new Exception("Student with id:" + studentId + "does not exist");
       }
       studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException("Student by ID" +studentId + "does not exist"));

        //If name provided is not the same as it is already
        if(name!=null && name.length()>0
                && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
    }
}
