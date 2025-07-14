package com.belvinard.logisticsCompany.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "packages")
@Data
@NoArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageId;

    private String description;
    private Double weight;
    private Boolean fragile;

    @Enumerated(EnumType.STRING)
    private PackageStatus status;
}

