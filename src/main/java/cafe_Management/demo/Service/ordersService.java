package cafe_Management.demo.Service;

import cafe_Management.demo.model.RequestOrdersDto;

import java.util.List;

public interface ordersService {
    List<RequestOrdersDto> fetchAllOrders();
}
