package ru.kafpin.lab5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.kafpin.lab5.models.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByNameContainsIgnoreCase(String part);
    List<Student> findByDepartmentTitleIgnoreCase(String title);
    List<Student> findStudentByDepartment_TitleContainingIgnoreCase(String part);
}
