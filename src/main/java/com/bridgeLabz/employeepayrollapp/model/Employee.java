package com.bridgeLabz.employeepayrollapp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ✅ Auto-incrementing ID
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)    // ✅ Prevents `id` from appearing in Swagger request body
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must have at least 3 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can only contain letters and spaces")
    private String name;

    private double salary;

    @NotBlank(message = "Department cannot be empty")
    private String department;
}
