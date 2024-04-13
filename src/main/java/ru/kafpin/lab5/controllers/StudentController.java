package ru.kafpin.lab5.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kafpin.lab5.dtos.StudentDTO;
import ru.kafpin.lab5.models.Department;
import ru.kafpin.lab5.models.Student;
import ru.kafpin.lab5.repositories.DepartmentRepository;
import ru.kafpin.lab5.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()){
            throw new EntityNotFoundException("Студент не найден");
        }
        return student.get();
    }

    @GetMapping("/students")
    public List<Student> getStudents(@RequestParam(value = "depname", required = true) String depName){

        //List<Student> list studentRepository.findByNameContains("и");

        return studentRepository.findByDepartmentTitleIgnoreCase(depName);
    }

    @GetMapping("/allstudents")
    public List<Student> getAll(){
        return (List<Student>) studentRepository.findAll();
    }

    @PostMapping("/new")
    public Student newStudent(@RequestBody StudentDTO studentDTO){
        System.out.println("Получен POST запрос");
        Department department = departmentRepository.findByTitle(studentDTO.getDepartment()).get();
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setDepartment(department);
        return studentRepository.save(student);
    }

    @PostMapping("/newfull")
    public Student newStudentFull(@RequestBody Student student){
        System.out.println("Получен POST запрос FULL");

        return studentRepository.save(student);
    }

    @GetMapping("/indepartment")
    public List<Student> getInDep(@RequestParam(value = "deppart") String part){
        return studentRepository.findStudentByDepartment_TitleContainingIgnoreCase(part);
    }

    @PutMapping("/student/{id}")
    public Student updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable("id") Long id){
        Optional<Student> studentOpt = studentRepository.findById(id);
        if(studentOpt.isEmpty()){
            throw new EntityNotFoundException("Студент не найден");
        }

        Optional<Department> department = departmentRepository.findByTitle(studentDTO.getDepartment());
        if(department.isEmpty()){
            throw new EntityNotFoundException("Некорректная группа");
        }
        Student student = new Student();
        student.setId(studentOpt.get().getId());
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setDepartment(department.get());
        return studentRepository.save(student);

    }

}
