package ua.hillel.springdataapp.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Integer id;
    private Date date;
    private Double cost;
    private List<ProductDTO> products;
}
