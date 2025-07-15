package com.belvinard.logisticsCompany.payload;

import com.belvinard.logisticsCompany.model.PackageStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * A Data Transfer Object (DTO) for package requests.
 *
 * This record encapsulates the details required for creating or updating a package request,
 * including a description, weight, fragility status, and the current status of the package.
 *
 * @param description A brief description of the package. Must be at least 4 characters long.
 * @param weight The weight of the package. Must be a positive value.
 * @param fragile Indicates whether the package is fragile.
 * @param status The current status of the package.
 */
public record PackageRequestDTO(
        @NotBlank @Size(min = 4, max = 255) String description,
        @NotNull @Positive Double weight,
        @NotNull Boolean fragile,
        @NotNull PackageStatus status
) {}
