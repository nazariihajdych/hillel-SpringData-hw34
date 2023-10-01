package ua.hillel.springdataapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hillel.springdataapp.model.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
