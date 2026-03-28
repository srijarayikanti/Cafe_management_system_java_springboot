package cafe_Management.demo.controller;

import cafe_Management.demo.enitites.Customer;
import cafe_Management.demo.model.RequestCustomerBilling;
import cafe_Management.demo.model.customerRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public interface customerController {

    @Operation(
            summary="saveCustomerDetails",
            operationId="saveCustomerDetails",
            tags="Customer",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/saveCustomerDetails",
            produces = "application/json",
            consumes = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> saveCustomerDetails(@RequestBody customerRequestDto request);

    @Operation(
            summary="saveCustomerBillingDetails",
            operationId="saveCustomerBillingDetails",
            tags="Customer",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/saveCustomerBillingDetails",
            produces = "application/json",
            consumes = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> saveCustomerBillingDetails(@RequestBody RequestCustomerBilling request);
}
