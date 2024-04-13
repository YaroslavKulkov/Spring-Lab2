package ru.kafpin.lab5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kafpin.lab5.models.Department;
import ru.kafpin.lab5.repositories.DepartmentRepository;
import ru.kafpin.lab5.repositories.StudentRepository;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepository;
    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("department", new Department());
        return "new_department";
    }

    @PostMapping("/new")
    public String newStudent(@ModelAttribute Department department, Model model) {
        departmentRepository.save(department);
        return "redirect:/students/main";
    }
}
