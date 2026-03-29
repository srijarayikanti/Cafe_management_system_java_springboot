package cafe_Management.demo.controller;

import cafe_Management.demo.enitites.Customer;
import cafe_Management.demo.enitites.User;
import cafe_Management.demo.model.RequestUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {
    @Operation(
            summary="login",
            operationId="login",
            tags="login",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = User.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login/saveUserDetails",
            produces = "application/json",
            consumes = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> saveUserDetails(@RequestBody RequestUser requestUser);
}
