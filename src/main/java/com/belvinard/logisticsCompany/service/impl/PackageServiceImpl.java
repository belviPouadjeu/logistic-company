package com.belvinard.logisticsCompany.service.impl;

import com.belvinard.logisticsCompany.exceptions.APIException;
import com.belvinard.logisticsCompany.mapper.PackageMapper;
import com.belvinard.logisticsCompany.model.PackageStatus;
import com.belvinard.logisticsCompany.payload.PackageRequestDTO;
import com.belvinard.logisticsCompany.payload.PackageResponseDTO;
import com.belvinard.logisticsCompany.repository.PackageRepository;
import com.belvinard.logisticsCompany.service.PackageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@Transactional
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final PackageRepository pkgRepo;
    private final PackageMapper pkgMapper;

    @Override
    public PackageResponseDTO createPackage(PackageRequestDTO request) {
        validateWeight(request.weight());
        validateInitialStatus(request.status());

        Package pkg = pkgMapper.toEntity(request);
        Package saved = pkgRepo.save(pkg);
        return pkgMapper.toResponseDto(saved);
    }

    private void validateWeight(Double weight) {
        double maxWeight = 50.0;
        if (weight > maxWeight) {
            throw new APIException(
                    String.format("Weight must not exceed %.1f kg", maxWeight)
            );
        }
    }

    private void validateInitialStatus(PackageStatus status) {
        var invalid = EnumSet.of(
                PackageStatus.DELIVERED,
                PackageStatus.RETURNED,
                PackageStatus.FAILED_DELIVERY
        );
        if (invalid.contains(status)) {
            throw new APIException("Status must be an initial state (e.g. PENDING)");
        }
    }




}