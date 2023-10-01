package ua.hillel.springdataapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity(name = "product")
@Table(name = "t_product")
@ToString(exclude = "order")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double cost;
    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;
}
