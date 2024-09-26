package sda.spring.productmanagement.controllers;



import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.spring.productmanagement.Test;
import sda.spring.productmanagement.dto.ProductDTO;
import sda.spring.productmanagement.services.ProductService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {

    private final ProductService productService;
    private final Test test;

    public ProductController(ProductService productService, Test test) {
        this.productService = productService;
        this.test = test;
    }


    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDto) {
        ProductDTO savedProduct = productService.createProduct(productDto);
        this.test.printRandomNumber();

        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/paginated")
    public Page<ProductDTO> listProductsPaginated(@RequestParam int page, @RequestParam int size) {
        return productService.getProductsPaginated(page, size);
    }
    @GetMapping
    public ResponseEntity<List<ProductDTO>> fetchAllProducts() {
        List<ProductDTO> products = productService.listAllProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> fetchProduct(@PathVariable Long id) {
        Optional<ProductDTO> product = productService.getProductById(id);

        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO product) {
        Optional<ProductDTO> foundProduct = productService.getProductById(id);

        if(foundProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        product.setId(id);
        ProductDTO savedProduct = productService.updateProduct(product);

        return ResponseEntity.ok(savedProduct);
    }

}
