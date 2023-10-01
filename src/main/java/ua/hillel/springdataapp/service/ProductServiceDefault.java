package ua.hillel.springdataapp.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.springdataapp.model.dto.ProductDTO;
import ua.hillel.springdataapp.model.entity.Order;
import ua.hillel.springdataapp.model.entity.Product;
import ua.hillel.springdataapp.model.mapper.ProductMapper;
import ua.hillel.springdataapp.repo.OrderRepo;
import ua.hillel.springdataapp.repo.ProductRepo;

@Service
@RequiredArgsConstructor
public class ProductServiceDefault implements ProductService{
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final ProductMapper productMapper;
    @Override
    public ProductDTO addProductForOrderId(Integer orderId, ProductDTO productDTO) {
        Order order = orderRepo.getReferenceById(orderId);

        if (order == null) {
            throw new  EntityNotFoundException("order with id:" + orderId + " is not found");
        }

        order.setCost(order.getCost() + productDTO.getCost());

        Product product = productMapper.productDTOToProduct(productDTO);
        product.setOrder(order);

        Product saveProduct = productRepo.save(product);

        return productMapper.productToProductDTO(saveProduct);
    }
}
