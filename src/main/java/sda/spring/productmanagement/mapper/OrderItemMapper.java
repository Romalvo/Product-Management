package sda.spring.productmanagement.mapper;

import sda.spring.productmanagement.dto.OrderItemDTO;
import sda.spring.productmanagement.entities.OrderItemEntity;

public class OrderItemMapper {

    public static OrderItemDTO toOrderItemDTO(OrderItemEntity orderItemEntity) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItemEntity.getId());
        orderItemDTO.setPrice(orderItemEntity.getPrice());
        orderItemDTO.setQuantity(orderItemEntity.getQuantity());
        orderItemDTO.setProductId(orderItemEntity.getProductEntity().getId());

        if(orderItemEntity.getOrderEntity() != null) {
            orderItemDTO.setProduct(ProductMapper.toProductDto(orderItemEntity.getProductEntity()));
        }

        return orderItemDTO;
    }

    public static OrderItemEntity toOrderItemEntity(OrderItemDTO orderItemDTO) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setId(orderItemDTO.getId());
        orderItemEntity.setPrice(orderItemDTO.getPrice());
        orderItemEntity.setQuantity(orderItemDTO.getQuantity());

        if(orderItemDTO.getProductId() != null) {
            orderItemEntity.setProductEntity(ProductMapper.toProductEntity(orderItemDTO.getProduct()));
        }
        return orderItemEntity;
    }
}
