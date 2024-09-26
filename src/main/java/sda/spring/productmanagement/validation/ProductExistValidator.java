package sda.spring.productmanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sda.spring.productmanagement.dto.OrderDTO;
import sda.spring.productmanagement.dto.OrderItemDTO;
import sda.spring.productmanagement.repositories.ProductRepository;

import java.util.List;

public class ProductExistValidator implements ConstraintValidator<ValidProducts, OrderDTO> {
    private final ProductRepository productRepository;

    public ProductExistValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isValid(OrderDTO orderDTO, ConstraintValidatorContext context) {
        List<Long> productsIds = orderDTO.getOrderItems().stream()
                .map(OrderItemDTO::getProductId)
                .toList();

        long existingProductsCount = productRepository.countByIdIn(productsIds);
        return existingProductsCount == productsIds.size();
    }
}
