package ua.hillel.springdataapp.service;



import ua.hillel.springdataapp.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO getOrderById(Integer id);
    List<OrderDTO> getAllOrders();
    OrderDTO addOrder(OrderDTO orderDTO);
    void removeOrder(OrderDTO orderDTO);
}
