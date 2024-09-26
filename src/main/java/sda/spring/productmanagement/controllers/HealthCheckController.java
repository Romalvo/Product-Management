package sda.spring.productmanagement.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/healthcheck")
public class HealthCheckController {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok(activeProfile);
    }
}
