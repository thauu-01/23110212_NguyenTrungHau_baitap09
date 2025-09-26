package baitap09.resolver;

import baitap09.entity.CategoryEntity;
import baitap09.entity.ProductEntity;
import baitap09.entity.UserEntity;
import baitap09.repository.CategoryRepository;
import baitap09.repository.ProductRepository;
import baitap09.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller // Spring GraphQL dùng @Controller thay vì @Component
public class QueryResolver {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    // ================== PRODUCT ==================
    @QueryMapping
    public List<ProductEntity> allProductsSortedByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<ProductEntity> productsByCategory(@Argument Long categoryId) {
        return productRepository.findByCategoriesId(categoryId);
    }

    // ================== USER ==================
    @QueryMapping
    public List<UserEntity> allUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public UserEntity userById(@Argument Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // ================== CATEGORY ==================
    @QueryMapping
    public List<CategoryEntity> allCategories() {
        return categoryRepository.findAll();
    }

    @QueryMapping
    public CategoryEntity categoryById(@Argument Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
