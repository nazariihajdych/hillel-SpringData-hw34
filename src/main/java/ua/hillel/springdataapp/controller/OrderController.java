package ua.hillel.springdataapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hillel.springdataapp.exeption.EntityNotFoundException;
import ua.hillel.springdataapp.model.dto.OrderDTO;
import ua.hillel.springdataapp.model.dto.ProductDTO;
import ua.hillel.springdataapp.service.OrderService;
import ua.hillel.springdataapp.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer id) throws EntityNotFoundException {
        log.info("OrderController grtOrderById");
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<OrderDTO>> getAllOrders(){
        log.info("OrderController getAllOrders");
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO orderDTO){
        log.info("OrderController saveOrder");
        return ResponseEntity.ok(orderService.addOrder(orderDTO));
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void removeOrder(@RequestBody OrderDTO orderDTO){
        orderService.removeOrder(orderDTO);
    }

    @PostMapping(value = {"/product"}, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO){
        ProductDTO savedProduct = productService.addProductForOrderId(productDTO.getOrderId(), productDTO);
        return ResponseEntity.ok(savedProduct);
    }
}
