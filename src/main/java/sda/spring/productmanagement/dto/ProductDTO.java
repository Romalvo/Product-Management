package sda.spring.productmanagement.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO extends OrderItemDTO {

    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Price is required and cannot be null")
    @DecimalMin(value = "0.0", message = "Price must be positive")
    private double price;

    private String description;
}
