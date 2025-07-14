package com.belvinard.logisticsCompany.mapper;

import com.belvinard.logisticsCompany.payload.PackageRequestDTO;
import com.belvinard.logisticsCompany.payload.PackageResponseDTO;


@Mapper(componentModel = "spring")
public interface PackageMapper {
    Package toEntity(PackageRequestDTO dto);
    PackageResponseDTO toResponseDto(Package pkg);
}
