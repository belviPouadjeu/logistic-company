package com.belvinard.logisticsCompany.mapper;

import com.belvinard.logisticsCompany.payload.PackageRequestDTO;
import com.belvinard.logisticsCompany.payload.PackageResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PackageMapper {
    Package toEntity(PackageRequestDTO dto);
    PackageResponseDTO toResponseDto(Package pkg);
}
