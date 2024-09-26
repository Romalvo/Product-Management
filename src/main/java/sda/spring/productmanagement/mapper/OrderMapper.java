package sda.spring.productmanagement.mapper;

import sda.spring.productmanagement.dto.OrderDTO;
import sda.spring.productmanagement.dto.OrderItemDTO;
import sda.spring.productmanagement.entities.OrderEntity;
import sda.spring.productmanagement.entities.OrderItemEntity;


import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static OrderDTO toOrderDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setCustomerName(orderEntity.getCustomerName());

        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        for (OrderItemEntity orderItemEntity: orderEntity.getOrderItemEntities()) {
            orderItemDTOS.add(
                    OrderItemMapper.toOrderItemDTO(orderItemEntity)
            );
        }
        orderDTO.setOrderItems(orderItemDTOS);

        return orderDTO;
    }

    public static OrderEntity toOrderEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setCustomerName(orderDTO.getCustomerName());

        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        for (OrderItemDTO orderItemDTO: orderDTO.getOrderItems()) {
            orderItemEntities.add(
                    OrderItemMapper.toOrderItemEntity(orderItemDTO)
            );
        }
        orderEntity.setOrderItemEntities(orderItemEntities);

        return orderEntity;
    }
}
