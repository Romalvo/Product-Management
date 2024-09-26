package sda.spring.productmanagement.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.spring.productmanagement.entities.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
