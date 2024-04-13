package ru.kafpin.lab5.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
    private String name;
    private int age;
    private String department;
}
