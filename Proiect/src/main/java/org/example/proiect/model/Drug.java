package org.example.proiect.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "drug_storage")
@RequiredArgsConstructor
@Data
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String manufacturer;
    private String description;
    private String sideEffects;
    private String dosage;
    private Long quantity;

}
