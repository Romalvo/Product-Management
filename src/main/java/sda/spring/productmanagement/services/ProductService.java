package sda.spring.productmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sda.spring.productmanagement.dto.ProductDTO;
import sda.spring.productmanagement.entities.ProductEntity;
import sda.spring.productmanagement.exception.TooHighPriceException;
import sda.spring.productmanagement.mapper.ProductMapper;
import sda.spring.productmanagement.repositories.ProductRepository;


import java.util.List;
import java.util.Optional;



@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public ProductDTO createProduct(ProductDTO product) {
        if(product.getPrice() > 1000_000){
            throw new TooHighPriceException();
        }

        ProductEntity productEntity = this.productRepository.save(ProductMapper.toProductEntity(product));
        return ProductMapper.toProductDto(productEntity);

    }

    public List<ProductDTO> listAllProducts() {
        List<ProductEntity> productEntities = this.productRepository.findAll();

        return ProductMapper.toProductDTOs(productEntities);
    }

    public Page<ProductDTO> getProductsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);

        return productEntityPage.map(ProductMapper::toProductDto);
    }

    public Optional<ProductDTO> getProductById(Long id) {
        Optional<ProductEntity> productEntity = this.productRepository.findById(id);
        return productEntity.map(ProductMapper::toProductDto);
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    public ProductDTO updateProduct(ProductDTO product) {
        ProductEntity productEntity = this.productRepository.save(ProductMapper.toProductEntity(product));

        return ProductMapper.toProductDto(productEntity);
    }

}
