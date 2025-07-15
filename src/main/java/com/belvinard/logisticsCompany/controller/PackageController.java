package com.belvinard.logisticsCompany.controller;

import com.belvinard.logisticsCompany.payload.PackageRequestDTO;
import com.belvinard.logisticsCompany.payload.PackageResponse;
import com.belvinard.logisticsCompany.payload.PackageResponseDTO;
import com.belvinard.logisticsCompany.service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Package Management", description = "APIs for managing packages in the logistics system")
@RestController
@RequestMapping("${api.prefix}/packages")
@RequiredArgsConstructor
public class PackageController {
    private final PackageService packageService;

    @Operation(
            summary = "Create a new package",
            description = "Creates a new package in the system with the provided details"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Package successfully created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PackageResponseDTO.class))
    )
    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    @ApiResponse(responseCode = "409", description = "Package with the same description already exists", content = @Content)
    @PostMapping("/create")
    public ResponseEntity<PackageResponseDTO> createPackage(
            @Valid @RequestBody PackageRequestDTO request) {
        var response = packageService.createPackage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Get paginated packages",
            description = "Retrieve a paginated list of packages, with sorting options"
    )
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid parameters")
    @GetMapping
    public ResponseEntity<PackageResponse> getAllPackages(
            @Parameter(description = "Page number (0-based)", example = "0")
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,

            @Parameter(description = "Page size", example = "5")
            @RequestParam(value = "pageSize", required = false) Integer pageSize,

            @Parameter(description = "Field to sort by", example = "packageId")
            @RequestParam(value = "sortBy", required = false) String sortBy,

            @Parameter(description = "Sort direction: asc or desc", example = "asc",
                    schema = @Schema(allowableValues = {"asc", "desc"}))
            @RequestParam(value = "sortOrder", required = false) String sortOrder
    ) {
        PackageResponse response = packageService.getAllPackages(
                pageNumber, pageSize, sortBy, sortOrder
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
