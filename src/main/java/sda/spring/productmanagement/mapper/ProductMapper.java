package sda.spring.productmanagement.mapper;

import sda.spring.productmanagement.dto.ProductDTO;
import sda.spring.productmanagement.entities.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static ProductEntity toProductEntity(ProductDTO productDto) {
        ProductEntity product = new ProductEntity();
        product.setName(productDto.getName());
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        return product;
    }

    public static ProductDTO toProductDto(ProductEntity product) {
        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());

        return productDto;
    }

    public static List<ProductEntity> toProductEntities(List<ProductDTO> productDtos) {
        List<ProductEntity> productEntities = new ArrayList<>();
        productDtos.forEach(productDto ->productEntities.add(ProductMapper.toProductEntity(productDto)));

        return productEntities;
    }

    public static List<ProductDTO> toProductDTOs(List<ProductEntity> productEntities) {
        List<ProductDTO> productDtos = new ArrayList<>();
        productEntities.forEach(productEntity ->productDtos.add(ProductMapper.toProductDto(productEntity)));

        return productDtos;
    }
}
