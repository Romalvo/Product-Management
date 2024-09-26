package sda.spring.productmanagement.entities;


import ch.qos.logback.core.status.Status;
import jakarta.annotation.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.LOW;

    @Enumerated(EnumType.STRING)
    private Department department = Department.SALES;

    @NotBlank(message = "Description is mandatory")
    @Size(min =20, message = "Description should have at least 20 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status Status.OPEN;

    private LocalDateTime creationDate = LocalDateTime.now();

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Conversation> conversations;
}

enum Priority{
    HIGH, MEDIUM, LOW
}

enum Department{
    TECH, SALES, FINANCE
}

enum Status {
    OPEN, IN_PROGRESS, CLOSED
}
