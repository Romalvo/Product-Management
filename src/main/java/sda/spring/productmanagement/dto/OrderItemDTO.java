package sda.spring.productmanagement.dto;


import lombok.Data;

@Data
public class OrderItemDTO {

    private Long id;
    private Long productId;
    private ProductDTO product;
    private int quantity;
    private double price;
}
