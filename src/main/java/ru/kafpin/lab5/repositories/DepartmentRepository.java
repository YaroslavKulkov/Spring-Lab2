package ru.kafpin.lab5.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kafpin.lab5.models.Department;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Optional<Department> findByTitle(String title);
}
