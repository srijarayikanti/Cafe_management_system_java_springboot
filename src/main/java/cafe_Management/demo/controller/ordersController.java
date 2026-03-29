package cafe_Management.demo.controller;

import cafe_Management.demo.model.RequestOrdersDto;

import java.util.List;

public interface ordersController {
    List<RequestOrdersDto> fetchAllOrders();
}
