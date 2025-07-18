package com.example.StudentAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

  
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "listStudent"; 
    }

    // Show form to register new student
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "registerStudent";
    }

    // Save student
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }

 
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "registerStudent";
        } else {
            return "redirect:/students";
        }
    }

   
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student updatedStudent) {
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent != null) {
            existingStudent.setFirst_name(updatedStudent.getFirst_name());
            existingStudent.setFirst_name(updatedStudent.getFirst_name());
            existingStudent.setGender(updatedStudent.getGender());
            existingStudent.setDate_of_birth(updatedStudent.getDate_of_birth());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setPhone(updatedStudent.getPhone());
            existingStudent.setAddress(updatedStudent.getAddress());
            existingStudent.setCity(updatedStudent.getCity());
            existingStudent.setState(updatedStudent.getState());
            existingStudent.setPincode(updatedStudent.getPincode());
            existingStudent.setEnrollment_date(updatedStudent.getEnrollment_date());
            existingStudent.setCourse(updatedStudent.getCourse());

            studentService.updateStudent(existingStudent);
        }
        return "redirect:/students";
    }

   
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
