package cafe_Management.demo.controller;

import cafe_Management.demo.enitites.Customer;
import cafe_Management.demo.enitites.KitchenTeam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface kTeamController {
    @Operation(
            summary="Fetch all orders",
            operationId="saveKTeamDetails",
            tags="KitchenTeam",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = KitchenTeam.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = KitchenTeam.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/saveKTeamDetails",
            produces = "application/json",
            consumes = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> saveKTeamDetails(@RequestBody KitchenTeam request);

    @Operation(
            summary="Fetch all orders",
            operationId="fetchKTeamDetails",
            tags="KitchenTeam",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = KitchenTeam.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = KitchenTeam.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/fetchKTeamDetails",
            produces = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> fetchKTeamDetails();
}
