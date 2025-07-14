package com.belvinard.logisticsCompany.payload;

import com.belvinard.logisticsCompany.model.PackageStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PackageRequestDTO(
    @NotBlank @Size(min = 4) String description,
    @NotNull @Positive Double weight,
    @NotNull Boolean fragile,
    @NotNull PackageStatus status
) {}
