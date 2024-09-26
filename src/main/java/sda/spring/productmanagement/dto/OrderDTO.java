package sda.spring.productmanagement.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sda.spring.productmanagement.validation.ValidProducts;

import java.util.List;

@Data
@ValidProducts
public class OrderDTO {
    private Long id;

    @NotBlank
    @Size(min = 5, max = 100, message = "Customer name must be between 2 and 100 characters")
    private String customerName;
    private List<OrderItemDTO> orderItems;
}
