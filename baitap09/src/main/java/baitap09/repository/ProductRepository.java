package baitap09.repository;

import baitap09.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByOrderByPriceAsc();
    List<ProductEntity> findByCategoriesId(Long categoryId);
}