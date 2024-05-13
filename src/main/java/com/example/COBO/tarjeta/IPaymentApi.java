package com.example.COBO.tarjeta;

import org.springframework.http.ResponseEntity;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface IPaymentApi {

    @Tag(name = "Payment", description = "Process payment")
    @Operation(summary = "Process payment", description = "Endpoint to process credit card payment.")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "Payment processed successfully",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponse.class))
                }
            ),
            @ApiResponse(
                responseCode = "400", description = "Bad Request",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponse.class))
                }
            ),
            @ApiResponse(
                responseCode = "500", description = "Internal Server Error",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponse.class))
                }
            )
        }
    )
    public ResponseEntity<PaymentResponse> processPayment(PaymentRequest request);
}
