package ua.hillel.springdataapp.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.hillel.springdataapp.model.dto.OrderDTO;
import ua.hillel.springdataapp.model.entity.Order;
import ua.hillel.springdataapp.model.entity.Product;
import ua.hillel.springdataapp.model.mapper.OrderMapper;
import ua.hillel.springdataapp.repo.OrderRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceDefault implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;

    @Override
    public OrderDTO getOrderById(Integer id) {
        log.info("OrderServiceDefault getOrderById method invoke");
        Order order = orderRepo.
                findById(id).
                orElseThrow(() -> new EntityNotFoundException("order with id:" + id + " is not found"));
        return orderMapper.orderToOrderDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        log.info("OrderServiceDefault getAllOrders method invoke");
        List<Order> orders = orderRepo.findAll();
        return orders.stream().map(orderMapper::orderToOrderDTO).toList();
    }

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        log.info("addOrder method invoke");
        log.info("addOrder counting cost of order");
        Order order = orderMapper.orderDTOToOrder(orderDTO);
        Double costOfProductsInOrder = 0D;
        for (Product p : order.getProducts()) {
            costOfProductsInOrder += p.getCost();
        }
        order.setCost(costOfProductsInOrder);
        log.info("addOrder adding user");
        orderRepo.save(order);
        return orderMapper.orderToOrderDTO(order);
    }

    @Override
    public void removeOrder(OrderDTO orderDTO) {
        log.info("removeOrder method invoke");
        orderRepo.delete(orderMapper.orderDTOToOrder(orderDTO));
    }
}
