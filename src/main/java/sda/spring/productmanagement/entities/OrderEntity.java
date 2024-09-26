package sda.spring.productmanagement.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    @OneToMany(mappedBy = "orderEntity")
    private List<OrderItemEntity> orderItemEntities;
}
