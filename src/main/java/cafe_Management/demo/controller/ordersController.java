package cafe_Management.demo.controller;

import cafe_Management.demo.enitites.Customer;
import cafe_Management.demo.model.RequestOrdersDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ordersController {

    @Operation(
            summary="Fetch all orders",
            operationId="fetchAllOrders",
            tags="Orders",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/fetchAllOrders",
            produces = "application/json"
    )
    @CrossOrigin
    ResponseEntity<List<RequestOrdersDto>> fetchAllOrders();
}
