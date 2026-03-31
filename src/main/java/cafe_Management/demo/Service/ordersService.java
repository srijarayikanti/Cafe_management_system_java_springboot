package cafe_Management.demo.Service;

import cafe_Management.demo.model.RequestOrdersDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ordersService {
    ResponseEntity<List<RequestOrdersDto>> fetchAllOrders();
}
