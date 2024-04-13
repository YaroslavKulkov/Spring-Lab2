package ru.kafpin.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kafpin.lab5.dtos.StudentDTO;
import ru.kafpin.lab5.models.Department;
import ru.kafpin.lab5.models.Student;
import ru.kafpin.lab5.repositories.DepartmentRepository;
import ru.kafpin.lab5.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/students")
@Controller
public class MainController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/main")
    public String getStudents(Model model) {
        List<Student> students = (List<Student>) studentRepository.findAll();
        model.addAttribute("students", students);
        return "main";
    }

    @GetMapping("/write/{id}")
    public String writeEmail(Model model, @PathVariable("id") Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        //Write mail
        return "redirect:/students/main";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            return "redirect:/students/main";
        }
        model.addAttribute("selectedStudent", optionalStudent.get());
        return "details";
    }

    @GetMapping("/update/{id}")
    public String editStudent(Model model, @PathVariable("id") Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            return "redirect:/students/main";
        }
        model.addAttribute("student", optionalStudent.get());
        return "edit_student";
    }

    @PostMapping("/update")
    public String editStudent(@ModelAttribute Student student, Model model) {
        studentRepository.save(student);
        return "redirect:/students/main";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        }
        return "redirect:/students/main";
    }

    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "new_student";
    }

    @PostMapping("/new")
    public String newStudent(@ModelAttribute StudentDTO studentDTO, Model model) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        Department department = departmentRepository.findByTitle(studentDTO.getDepartment()).get();
        student.setDepartment(department);
        studentRepository.save(student);
        return "redirect:/students/main";
    }
}