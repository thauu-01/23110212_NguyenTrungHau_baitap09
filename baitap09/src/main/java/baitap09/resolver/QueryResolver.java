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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class QueryResolver {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    // ================== PRODUCT ==================

    // Lấy tất cả sản phẩm (không sắp xếp)
    @QueryMapping
    public List<ProductEntity> allProducts() {
        List<ProductEntity> products = productRepository.findAll();
        // Đảm bảo không trả về null và không có phần tử null
        return products == null ? new ArrayList<>() :
                products.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
    }

    // Lấy tất cả sản phẩm sắp xếp theo giá tăng dần
    @QueryMapping
    public List<ProductEntity> allProductsSortedByPrice() {
        List<ProductEntity> products = productRepository.findAllByOrderByPriceAsc();
        return products == null ? new ArrayList<>() :
                products.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
    }

    // Lấy tất cả sản phẩm theo Category ID
    @QueryMapping
    public List<ProductEntity> productsByCategory(@Argument Long categoryId) {
        List<ProductEntity> products = productRepository.findByCategories_Id(categoryId);
        return products == null ? new ArrayList<>() :
                products.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
    }

    // ================== USER ==================
    @QueryMapping
    public List<UserEntity> allUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users == null ? new ArrayList<>() :
                users.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
    }

    @QueryMapping
    public UserEntity userById(@Argument Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // ================== CATEGORY ==================
    @QueryMapping
    public List<CategoryEntity> allCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories == null ? new ArrayList<>() :
                categories.stream()
                          .filter(Objects::nonNull)
                          .collect(Collectors.toList());
    }

    @QueryMapping
    public CategoryEntity categoryById(@Argument Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
