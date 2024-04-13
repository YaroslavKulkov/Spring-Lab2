package ru.kafpin.lab5.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
@Table(name = "students")
public class Student {
    //@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY для MySQL SEQUENCE
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    private int age;

    @ManyToOne
    //@JsonManagedReference
    @JoinColumn(name = "department_id")
    private Department department;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", department=" + department +
                '}';
    }

}
