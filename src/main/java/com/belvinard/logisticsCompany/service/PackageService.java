package com.belvinard.logisticsCompany.service;


import com.belvinard.logisticsCompany.payload.PackageRequestDTO;
import com.belvinard.logisticsCompany.payload.PackageResponse;
import com.belvinard.logisticsCompany.payload.PackageResponseDTO;

public interface PackageService {
    PackageResponseDTO createPackage(PackageRequestDTO request);
    PackageResponse getAllPackages(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    PackageResponseDTO getPackageById(Long id);

}
