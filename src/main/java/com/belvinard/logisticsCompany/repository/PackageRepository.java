package com.belvinard.logisticsCompany.repository;

import com.belvinard.logisticsCompany.model.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
}
