package com.belvinard.logisticsCompany.service;

import com.belvinard.logisticsCompany.payload.PackageRequestDTO;
import com.belvinard.logisticsCompany.payload.PackageResponseDTO;
import com.belvinard.logisticsCompany.model.PackageEntity;
import com.belvinard.logisticsCompany.model.PackageStatus;
import com.belvinard.logisticsCompany.exceptions.APIException;
import com.belvinard.logisticsCompany.mapper.PackageMapper;
import com.belvinard.logisticsCompany.repository.PackageRepository;
import com.belvinard.logisticsCompany.service.impl.PackageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PackageServiceImplTest {

    @Mock
    private PackageRepository pkgRepo;

    @Mock
    private PackageMapper pkgMapper;

    @InjectMocks
    private PackageServiceImpl service;

    private PackageRequestDTO validRequest;
    private PackageEntity pkgEntity;
    private PackageEntity savedEntity;

    @BeforeEach
    void setup() {
        validRequest = new PackageRequestDTO(
            "Test description",
            10.0,
            false,
            PackageStatus.PENDING
        );

        pkgEntity = new PackageEntity();
        pkgEntity.setDescription(validRequest.description());
        pkgEntity.setWeight(validRequest.weight());
        pkgEntity.setFragile(validRequest.fragile());
        pkgEntity.setStatus(validRequest.status());

        savedEntity = new PackageEntity();
        savedEntity.setPackageId(1L);
        savedEntity.setDescription(validRequest.description());
        savedEntity.setWeight(validRequest.weight());
        savedEntity.setFragile(validRequest.fragile());
        savedEntity.setStatus(validRequest.status());
    }

    @Test
    void createPackage_success() {
        when(pkgMapper.toEntity(validRequest)).thenReturn(pkgEntity);
        when(pkgRepo.save(pkgEntity)).thenReturn(savedEntity);
        when(pkgMapper.toResponseDto(savedEntity))
            .thenReturn(new PackageResponseDTO(
                savedEntity.getPackageId(),
                savedEntity.getDescription(),
                savedEntity.getWeight(),
                savedEntity.getFragile(),
                savedEntity.getStatus()
            ));

        PackageResponseDTO result = service.createPackage(validRequest);

        assertThat(result.packageId()).isEqualTo(savedEntity.getPackageId());
        verify(pkgRepo).save(pkgEntity);
    }

    @Test
    void createPackage_tooHeavy_throws() {
        PackageRequestDTO tooHeavy = new PackageRequestDTO(
            "Hefty item", 60.0, false, PackageStatus.PENDING
        );

        assertThatThrownBy(() -> service.createPackage(tooHeavy))
            .isInstanceOf(APIException.class)
            .hasMessageContaining("Weight must not exceed");

        verifyNoInteractions(pkgRepo, pkgMapper);
    }

    @Test
    void createPackage_invalidStatus_throws() {
        PackageRequestDTO badStatus = new PackageRequestDTO(
            "Fragile item", 5.0, true, PackageStatus.DELIVERED
        );

        assertThatThrownBy(() -> service.createPackage(badStatus))
            .isInstanceOf(APIException.class)
            .hasMessageContaining("Status must be an initial state");

        verifyNoInteractions(pkgRepo, pkgMapper);
    }
}