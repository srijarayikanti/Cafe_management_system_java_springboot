package cafe_Management.demo.Service;

import cafe_Management.demo.model.RequestCustomerBilling;
import cafe_Management.demo.model.customerRequestDto;
import org.springframework.http.ResponseEntity;

public interface customerService {
    ResponseEntity<?> saveCustomerDetails(customerRequestDto request);

    ResponseEntity<?> saveCustomerBillingDetails(RequestCustomerBilling request);
}
