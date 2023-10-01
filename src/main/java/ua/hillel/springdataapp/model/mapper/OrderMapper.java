package ua.hillel.springdataapp.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import ua.hillel.springdataapp.model.dto.OrderDTO;
import ua.hillel.springdataapp.model.dto.ProductDTO;
import ua.hillel.springdataapp.model.entity.Order;
import ua.hillel.springdataapp.model.entity.Product;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ProductMapper.class})
public abstract class OrderMapper {
    @Autowired
    protected ProductMapper productMapper;

    @Mapping(source = "products", target = "products", qualifiedByName = "ProductsToProductsDTO")
    public abstract OrderDTO orderToOrderDTO(Order order);
    public abstract Order orderDTOToOrder(OrderDTO orderDTO);

    @Named("ProductsToProductsDTO")
    public List<ProductDTO> ProductsToProductsDTO(List<Product> products) {
        return products.stream()
                .map(productMapper::productToProductDTO)
                .toList();
    }
}
