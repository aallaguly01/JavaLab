package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Long> {
}
