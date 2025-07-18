package com.example.StudentAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // सर्व Students ची यादी मिळवा
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ID नुसार Student मिळवा
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // नवीन Student सेव्ह करा
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    // Student माहिती update करा
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    // Student delete करा ID नुसार
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
