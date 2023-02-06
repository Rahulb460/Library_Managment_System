package com.example.LMSBackend.RequestDto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDto {
    private int age;
    @Column(nullable = false)
    private String name;
    private String country;

    @Column(name = "school_email", unique = true, nullable = false)
    private String email;
}
