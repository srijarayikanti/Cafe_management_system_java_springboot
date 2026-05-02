package cafe_Management.demo.Service;

import cafe_Management.demo.enitites.Order;
import cafe_Management.demo.model.CreateOrderRequest;
import cafe_Management.demo.model.RequestOrdersDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ordersService {
    ResponseEntity<List<RequestOrdersDto>> fetchAllOrders();

    ResponseEntity<?> saveOrderDetails(RequestOrdersDto request);

    Order createOrder(CreateOrderRequest request);

    Order createOrder(CreateOrderRequest request);

    Order getOrderById(Long orderId);

    List<Order> getOrdersByCustomer(Long customerId);

    void cancelOrder(Long orderId);
}
