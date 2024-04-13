package com.example.demo.Modal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String brand;
    private String color;
    private String licensePlate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
