package com.example.COBO;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface ILoginApi {

    @Tag(name = "Login", description = "Authenticate user")
    @Operation(summary = "Authenticate user", description = "Endpoint to authenticate user.")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "User authenticated",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))
                }
            ),
            @ApiResponse(
                responseCode = "401", description = "Unauthorized",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))
                }
            ),
            @ApiResponse(
                responseCode = "400", description = "Bad Request",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))
                }
            ),
            @ApiResponse(
                responseCode = "500", description = "Internal Server Error",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))
                }
            )
        
        }
    )
    public ResponseEntity<LoginResponse> login(LoginRequest request);
}
