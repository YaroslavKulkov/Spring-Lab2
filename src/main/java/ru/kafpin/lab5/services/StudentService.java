package ru.kafpin.lab5.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kafpin.lab5.repositories.StudentRepository;

public class StudentService {
    @Autowired
    StudentRepository studentRepository;
}
