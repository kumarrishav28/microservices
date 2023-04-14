package com.rishav.productservice.service;


import com.rishav.productservice.model.Product;
import com.rishav.productservice.dto.ProductRequest;
import com.rishav.productservice.dto.ProductResponse;
import com.rishav.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {


   private final ProductRepository productRepository;

    public  void createProduct(ProductRequest productRequest){

        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .Description(productRequest.getDescription()).build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());


    }

    public List<ProductResponse> getAllProducts(){
       List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {

       return ProductResponse.builder().
               Id(product.getId()).
               name(product.getName())
               .price(product.getPrice())
               .Description(product.getDescription()).build();

    }

}
