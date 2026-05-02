package cafe_Management.demo.Service;

import cafe_Management.demo.Repository.OrderRepository;
import cafe_Management.demo.Repository.ProductRepository;
import cafe_Management.demo.Repository.customerBillingRepository;
import cafe_Management.demo.Repository.customerRepository;
import cafe_Management.demo.enitites.*;
import cafe_Management.demo.model.CreateOrderRequest;
import cafe_Management.demo.model.OrderItemRequest;
import cafe_Management.demo.model.RequestCustomerBilling;
import cafe_Management.demo.model.RequestOrdersDto;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements ordersService{
    private final customerRepository customerRepository;
    private final customerBillingRepository customerBillingRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrdersServiceImpl(customerRepository customerRepository, customerBillingRepository customerBillingRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.customerBillingRepository = customerBillingRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<RequestOrdersDto>> fetchAllOrders() {
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

        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveOrderDetails(RequestOrdersDto request) {
        // Save customer details
        Customer customer = new Customer();
        BeanUtils.copyProperties(request, customer);
        customerRepository.save(customer);

        // Save billing details
        for (RequestCustomerBilling billingDto : request.getBillingDetails()) {
            CustomerBilling billing = new CustomerBilling();
            BeanUtils.copyProperties(billingDto, billing);
            billing.setCustomerId(customer.getCustomerId()); // set the foreign key
            customerBillingRepository.save(billing);
        }

        return new ResponseEntity<>("Order details saved successfully", HttpStatus.CREATED);
    }

    @Override
    public Order createOrder(CreateOrderRequest request) {

        // 1. Fetch customer
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2. Create order
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        // 3. Loop through items
        for (OrderItemRequest itemReq : request.getItems()) {

            // Fetch product
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Create order item
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(product.getPrice());
            item.setOrder(order);

            // Calculate total
            totalAmount += product.getPrice() * itemReq.getQuantity();

            orderItems.add(item);
        }

        // 4. Set items and total
        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        // 5. Save (cascade saves items)
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() == OrderStatus.PAID) {
            throw new RuntimeException("Cannot cancel a paid order");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
