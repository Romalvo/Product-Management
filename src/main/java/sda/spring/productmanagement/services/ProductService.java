package sda.spring.productmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.spring.productmanagement.entities.ProductEntity;
import sda.spring.productmanagement.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;



@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> getAllProducts() {

        return productRepository.findAll();
    }

    public Optional<ProductEntity> getProductById(Long id) {

        return productRepository.findById(id);
    }

    public ProductEntity createProduct(ProductEntity product) {

        return productRepository.save(product);
    }

    public ProductEntity updateProduct(Long id, ProductEntity productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setDescription(productDetails.getDescription());
            return productRepository.save(product);
        }).orElseThrow(() ->new RuntimeException("Product not find with id " + id));
    }
    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Product not find with id " + id);
        }
    }
}
