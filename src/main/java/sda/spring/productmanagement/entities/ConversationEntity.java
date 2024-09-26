package sda.spring.productmanagement.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ConversationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketEntity ticket;

    @Enumerated(EnumType.STRING)
    private Sender sender;

    @NotBlank(message = "Message is mandatory")
    private String message;

    private LocalDateTime timestamp = LocalDateTime.now();
}

enum Sender {
    CUSTOMER, SUPPORT
}
