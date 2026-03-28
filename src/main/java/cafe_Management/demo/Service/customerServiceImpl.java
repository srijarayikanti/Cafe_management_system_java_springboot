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
        customerRepository.save(customer);
        ResponseCustomer responseCustomer  = new ResponseCustomer();
        BeanUtils.copyProperties(customer,responseCustomer);
        responseCustomer.setMessage("Customer details saved successfully for customer : " + customer.getName());
        return ResponseEntity.ok(responseCustomer);
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
}
