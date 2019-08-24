package com.example.webshop.services;

import com.example.webshop.models.Product;
import com.example.webshop.repositories.ProductRepository;
import com.example.webshop.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("There is not Product"));
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        productRepository.findAll().forEach(list::add);
        return list;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public  void editProduct(Product product){

    }

    public void editProductQuantity(Product product, String action) {
        productRepository.findById(product.getId()).get().setQuantity(product.getQuantity() - 1);
    }

}
