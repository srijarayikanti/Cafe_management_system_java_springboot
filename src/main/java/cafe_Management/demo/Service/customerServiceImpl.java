package cafe_Management.demo.Service;

import cafe_Management.demo.Repository.customerBillingRepository;
import cafe_Management.demo.Repository.customerRepository;
import cafe_Management.demo.enitites.Customer;
import cafe_Management.demo.enitites.CustomerBilling;
import cafe_Management.demo.model.RequestCustomerBilling;
import cafe_Management.demo.model.ResponseCustomer;
import cafe_Management.demo.model.customerRequestDto;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class customerServiceImpl implements  customerService {
    private final customerRepository customerRepository;
    private final customerBillingRepository customerBillingRepository;

    public customerServiceImpl(userService userService, customerRepository customerRepository, customerBillingRepository customerBillingRepository) {
        this.customerRepository = customerRepository;
        this.customerBillingRepository = customerBillingRepository;
    }

    @Override
    public ResponseEntity<?> saveCustomerDetails(customerRequestDto request) {
        Customer customer=new Customer();
        BeanUtils.copyProperties(request,customer);
        customer.setVisitTime(LocalDateTime.now());
        createLoginId(customer);
        customerRepository.save(customer);
        ResponseCustomer responseCustomer  = new ResponseCustomer();
        BeanUtils.copyProperties(customer,responseCustomer);
        responseCustomer.setMessage("Customer details saved successfully for customer : " + customer.getName());
        return ResponseEntity.ok(responseCustomer);
    }

    private void createLoginId(Customer customer) {
        String name = customer.getName();

        // 1. Get the first letter (lowercase)
        // Check if name is not null or empty first
        String firstLetter = (name != null && !name.isEmpty())
                ? name.substring(0, 1).toLowerCase()
                : "u"; // 'u' for unknown if name is missing

        // 2. Define the pattern (M = Month, dd = Day, yyyy = Year)
        // Use 'M' for single digit month (3) or 'MM' for double (03)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Mddyyyy");

        // 3. Get and format the current date
        String dateString = LocalDateTime.now().format(formatter);

        // 4. Combine: First Letter + Date (e.g., s3292026)
        String loginId = firstLetter + dateString;

        customer.setLoginId(loginId);
    }

    @Override
    public ResponseEntity<?> saveCustomerBillingDetails(RequestCustomerBilling request) {
         CustomerBilling customerBilling = new CustomerBilling();

         BeanUtils.copyProperties(request,customerBilling);
         customerBillingRepository.save(customerBilling);
            ResponseCustomer responseCustomer  = new ResponseCustomer();
            BeanUtils.copyProperties(customerBilling,responseCustomer);
            responseCustomer.setMessage("Customer billing details saved successfully for customer : " + customerBilling.getCustomerId());
            return ResponseEntity.ok(responseCustomer);
    }

    @Override
    public ResponseEntity<?> fetchCustomerDetailsByEmailId(String email) {
        List<Customer> customer = customerRepository.findByEmail(email);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        Customer responseCustomer = new Customer();
        BeanUtils.copyProperties(customer, responseCustomer);
        return ResponseEntity.ok(responseCustomer);
    }
}
