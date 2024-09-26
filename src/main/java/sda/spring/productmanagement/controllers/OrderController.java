package sda.spring.productmanagement.controllers;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.spring.productmanagement.dto.OrderDTO;
import sda.spring.productmanagement.services.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderDTO orderEntity = orderService.createOrder(orderDTO);

        return ResponseEntity.ok().body(orderEntity);
    }

    @PostMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable int id) {

    }
}
