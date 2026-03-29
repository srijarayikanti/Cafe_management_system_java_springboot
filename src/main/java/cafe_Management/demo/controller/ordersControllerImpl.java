package cafe_Management.demo.controller;

import cafe_Management.demo.Service.ordersService;
import cafe_Management.demo.model.RequestOrdersDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ordersControllerImpl implements ordersController {
    private final ordersService ordersService;

    public ordersControllerImpl(ordersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    public List<RequestOrdersDto> fetchAllOrders() {
        try {
            return ordersService.fetchAllOrders();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
