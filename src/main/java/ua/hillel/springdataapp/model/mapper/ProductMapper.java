package ua.hillel.springdataapp.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;;
import ua.hillel.springdataapp.model.dto.ProductDTO;
import ua.hillel.springdataapp.model.entity.Order;
import ua.hillel.springdataapp.model.entity.Product;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductMapper {
    @Mapping(source = "order", target = "orderId", qualifiedByName = "orderToOrderId")
    public abstract ProductDTO productToProductDTO(Product product);
    public abstract Product productDTOToProduct(ProductDTO productDTO);

    @Named("orderToOrderId")
    public Integer orderToOrderId(Order order) {
        if (order != null) {
            return order.getId();
        }
        return -1;
    }
}
