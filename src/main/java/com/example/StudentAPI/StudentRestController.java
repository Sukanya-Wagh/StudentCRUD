package com.example.StudentAPI;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
 

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    @Autowired
    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET all students
    @GetMapping
    public List<Student> listStudents() {
        return studentService.getAllStudents();
    }

    // POST - Add new student
    @PostMapping("/register")
    public Student saveStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return student;
    }

    // GET student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return student;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
    }

    // PUT - Update student
    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student stud) {
        Student existStudent = studentService.getStudentById(id);
        if (existStudent != null) {
            existStudent.setFirst_name(stud.getLast_name());
            existStudent.setFirst_name(stud.getLast_name());
            existStudent.setGender(stud.getGender());
            existStudent.setDate_of_birth(stud.getDate_of_birth());
            existStudent.setEmail(stud.getEmail());
            existStudent.setPhone(stud.getPhone());
            existStudent.setAddress(stud.getAddress());
            existStudent.setCity(stud.getCity());
            existStudent.setState(stud.getState());
            existStudent.setPincode(stud.getPincode());
            existStudent.setEnrollment_date(stud.getEnrollment_date());
            existStudent.setCourse(stud.getCourse());

            studentService.updateStudent(existStudent);
            return existStudent;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
    }

    // DELETE student
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return id + " Student deleted successfully";
    }
}
