package com.me.demospringmvc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;
    @Min(0)
    private double price;
    @Min(1)
    private int quantity;
}
