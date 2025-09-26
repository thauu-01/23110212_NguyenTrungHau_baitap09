package baitap09.resolver;

import baitap09.entity.CategoryEntity;
import baitap09.entity.ProductEntity;
import baitap09.entity.UserEntity;
import baitap09.repository.CategoryRepository;
import baitap09.repository.ProductRepository;
import baitap09.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MutationResolver {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // ========================= USER MUTATIONS =========================
    @MutationMapping
    public UserEntity createUser(@Argument UserInput input) {
        UserEntity user = new UserEntity();
        user.setFullname(input.fullname());
        user.setEmail(input.email());
        user.setPassword(input.password());
        user.setPhone(input.phone());

        if (input.categoryIds() != null) {
            List<CategoryEntity> categories = categoryRepository.findAllById(input.categoryIds());
            user.setCategories(categories);
        }
        return userRepository.save(user);
    }

    @MutationMapping
    public UserEntity updateUser(@Argument Long id, @Argument UserInput input) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (input.fullname() != null) user.setFullname(input.fullname());
        if (input.email() != null) user.setEmail(input.email());
        if (input.password() != null) user.setPassword(input.password());
        if (input.phone() != null) user.setPhone(input.phone());
        if (input.categoryIds() != null) {
            List<CategoryEntity> categories = categoryRepository.findAllById(input.categoryIds());
            user.setCategories(categories);
        }

        return userRepository.save(user);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        userRepository.deleteById(id);
        return true;
    }

    // ========================= PRODUCT MUTATIONS =========================
    @MutationMapping
    public ProductEntity createProduct(@Argument ProductInput input) {
        ProductEntity product = new ProductEntity();
        product.setTitle(input.title());
        product.setQuantity(input.quantity());
        product.setDesc(input.desc());
        product.setPrice(input.price());

        if (input.userid() != null) {
            UserEntity user = userRepository.findById(input.userid())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            product.setUser(user);
        }

        if (input.categoryIds() != null) {
            List<CategoryEntity> categories = categoryRepository.findAllById(input.categoryIds());
            product.setCategories(categories);
        }

        return productRepository.save(product);
    }

    @MutationMapping
    public ProductEntity updateProduct(@Argument Long id, @Argument ProductInput input) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (input.title() != null) product.setTitle(input.title());
        if (input.quantity() != null) product.setQuantity(input.quantity());
        if (input.desc() != null) product.setDesc(input.desc());
        if (input.price() != null) product.setPrice(input.price());

        if (input.userid() != null) {
            UserEntity user = userRepository.findById(input.userid())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            product.setUser(user);
        }

        if (input.categoryIds() != null) {
            List<CategoryEntity> categories = categoryRepository.findAllById(input.categoryIds());
            product.setCategories(categories);
        }

        return productRepository.save(product);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        productRepository.deleteById(id);
        return true;
    }

    // ========================= CATEGORY MUTATIONS =========================
    @MutationMapping
    public CategoryEntity createCategory(@Argument CategoryInput input) {
        CategoryEntity category = new CategoryEntity();
        category.setName(input.name());
        category.setImages(input.images());

        if (input.userIds() != null) {
            List<UserEntity> users = userRepository.findAllById(input.userIds());
            category.setUsers(users);
        }

        return categoryRepository.save(category);
    }

    @MutationMapping
    public CategoryEntity updateCategory(@Argument Long id, @Argument CategoryInput input) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (input.name() != null) category.setName(input.name());
        if (input.images() != null) category.setImages(input.images());
        if (input.userIds() != null) {
            List<UserEntity> users = userRepository.findAllById(input.userIds());
            category.setUsers(users);
        }

        return categoryRepository.save(category);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}

// ========================= INPUT CLASSES =========================
record UserInput(String fullname, String email, String password, String phone, List<Long> categoryIds) {}
record CategoryInput(String name, String images, List<Long> userIds) {}
record ProductInput(String title, Integer quantity, String desc, Double price, Long userid, List<Long> categoryIds) {}
