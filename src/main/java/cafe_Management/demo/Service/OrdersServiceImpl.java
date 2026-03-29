package cafe_Management.demo.Service;

import cafe_Management.demo.Repository.customerBillingRepository;
import cafe_Management.demo.Repository.customerRepository;
import cafe_Management.demo.enitites.Customer;
import cafe_Management.demo.enitites.CustomerBilling;
import cafe_Management.demo.model.RequestCustomerBilling;
import cafe_Management.demo.model.RequestOrdersDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements ordersService{
    private final customerRepository customerRepository;
    private final customerBillingRepository customerBillingRepository;

    public OrdersServiceImpl(customerRepository customerRepository, customerBillingRepository customerBillingRepository) {
        this.customerRepository = customerRepository;
        this.customerBillingRepository = customerBillingRepository;
    }

    @Override
    public List<RequestOrdersDto> fetchAllOrders() {
        List<Customer> customers = customerRepository.findAll();
        List<RequestOrdersDto> ordersList = new ArrayList<>();

        for (Customer customer : customers) {
            // fetch billing info for this customer
            List<CustomerBilling> billings = customerBillingRepository.findByCustomerId(customer.getCustomerId());

            // map CustomerBilling to RequestCustomerBilling
            List<RequestCustomerBilling> billingDetails = new ArrayList<>();
            for (CustomerBilling billing : billings) {
                RequestCustomerBilling billingDto = new RequestCustomerBilling();
                BeanUtils.copyProperties(billing, billingDto);
                billingDetails.add(billingDto);
            }

            // map Customer to RequestOrdersDto
            RequestOrdersDto ordersDto = new RequestOrdersDto();
            BeanUtils.copyProperties(customer, ordersDto);
            ordersDto.setBillingDetails(billingDetails);

            ordersList.add(ordersDto);
        }

        return ordersList;
    }
}
