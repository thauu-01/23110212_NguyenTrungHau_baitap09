package baitap09.repository;

import baitap09.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByOrderByPriceAsc();

 
    List<ProductEntity> findByCategories_Id(Long categoryId);
}
