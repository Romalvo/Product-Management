package sda.spring.productmanagement.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationError {

    private String fieldName;
    private List<String> errorMessage;
}
