package com.example.LMSBackend.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Entity
@Table(name = "students")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "school_email", unique = true, nullable = false)
    private String email;

    private int age;

    private String country;

    @CreationTimestamp
    private Date createdOn;

    @OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
    private Card card;

    @UpdateTimestamp
    private Date updatedOn;

    public Student(String name, String email, int age, String country) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;
    }
}
