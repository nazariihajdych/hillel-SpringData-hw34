package ua.hillel.springdataapp.service;

import ua.hillel.springdataapp.model.dto.ProductDTO;

public interface ProductService {
    ProductDTO addProductForOrderId(Integer orderId, ProductDTO productDTO);
}
