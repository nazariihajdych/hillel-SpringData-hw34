package ua.hillel.springdataapp.model.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private Double cost;
    private Integer orderId;
}
