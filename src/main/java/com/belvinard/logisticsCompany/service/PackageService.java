package com.belvinard.logisticsCompany.service;


import com.belvinard.logisticsCompany.payload.PackageRequestDTO;
import com.belvinard.logisticsCompany.payload.PackageResponseDTO;

public interface PackageService {
    PackageResponseDTO createPackage(PackageRequestDTO request);

}
