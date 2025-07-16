package com.belvinard.logisticsCompany.payload;

import com.belvinard.logisticsCompany.model.PackageStatus;

/**
 * A Data Transfer Object (DTO) for representing package information.
 *
 * @param packageId   the unique identifier of the package
 * @param description a brief description of the package
 * @param weight      the weight of the package
 * @param fragile     indicates if the package is fragile
 * @param status      the current status of the package
 */
public record PackageResponseDTO(
        Long packageId,
        String description,
        Double weight,
        Boolean fragile,
        PackageStatus status
) {}
