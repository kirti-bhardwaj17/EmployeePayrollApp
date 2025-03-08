package com.bridgeLabz.employeepayrollapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity  // ✅ Ensures Spring Boot recognizes this as a database entity
@Table(name = "employee")  // ✅ Maps to MySQL table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double salary;

    @Column(nullable = false)
    private String department;
}
