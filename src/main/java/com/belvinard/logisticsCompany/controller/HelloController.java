package com.belvinard.logisticsCompany.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Hello Controller", description = "A simple controller for testing API connectivity")
@RestController
public class HelloController {

    @Operation(
            summary = "Returns a 'Hello World!' message",
            description = "This is a simple test endpoint to verify that the service is up and running. It returns a static string."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the message",
            content = { @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication credentials are required", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden - You do not have permission to access this resource", content = @Content)
    @PostMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
