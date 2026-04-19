package cafe_Management.demo.controller;

import cafe_Management.demo.Service.customerService;
import cafe_Management.demo.model.RequestCustomerBilling;
import cafe_Management.demo.model.customerRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class customerControllerImpl  implements customerController {

    private final customerService customerService;

    public customerControllerImpl(customerService customerService) {
        this.customerService = customerService;
    }

    //private final String BASE_URL = "/api/customer";

    @Override
    public ResponseEntity<?> saveCustomerDetails(customerRequestDto request) {
        try {
            return customerService.saveCustomerDetails(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> saveCustomerBillingDetails(RequestCustomerBilling request) {
        try {
            return customerService.saveCustomerBillingDetails(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> fetchCustomerDetailsByEmailId(String email) {
        try {
            return customerService.fetchCustomerDetailsByEmailId(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
