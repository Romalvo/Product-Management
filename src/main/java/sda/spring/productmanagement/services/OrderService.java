package sda.spring.productmanagement.services;


import org.springframework.stereotype.Service;
import sda.spring.productmanagement.dto.OrderDTO;
import sda.spring.productmanagement.dto.OrderItemDTO;
import sda.spring.productmanagement.entities.OrderEntity;
import sda.spring.productmanagement.entities.OrderItemEntity;
import sda.spring.productmanagement.entities.ProductEntity;
import sda.spring.productmanagement.mapper.OrderMapper;
import sda.spring.productmanagement.repositories.OrderItemRepository;
import sda.spring.productmanagement.repositories.OrderRepository;
import sda.spring.productmanagement.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerName(orderDTO.getCustomerName());

        //Get list of products IDS from the order and check if they really exist in my products table
        List<Long> productsIds = orderDTO.getOrderItems()
                .stream()
                .map(OrderItemDTO::getProductId)
                .toList();

        //SELECT * FROM PRODUCTS WHERE id in (productsIds list)

        List<ProductEntity> productEntities = productRepository.findAllById(productsIds);

        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for(OrderItemDTO orderItemDTO : orderDTO.getOrderItems()) {
            ProductEntity productEntity = productEntities.stream()
                    .filter(item -> Objects.equals(item.getId(), orderItemDTO.getProductId()))
                    .toList()
                    .get(0);
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setPrice(productEntity.getPrice());
            orderItemEntity.setQuantity(orderItemDTO.getQuantity());
            orderItemEntity.setOrderEntity(orderEntity);
            orderItemEntity.setProductEntity(productEntity);
            orderItemEntities.add(orderItemEntity);
        }

        orderEntity.setOrderItemEntities(orderItemEntities);
        orderRepository.save(orderEntity);

        for(OrderItemEntity orderItem :orderEntity.getOrderItemEntities()) {
            orderItemRepository.save(orderItem);
        }

        return OrderMapper.toOrderDTO(orderEntity);

    }

}
