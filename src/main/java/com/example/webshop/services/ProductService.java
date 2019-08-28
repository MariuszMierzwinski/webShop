package com.example.webshop.services;

import com.example.webshop.exceptions.FileStorageException;
import com.example.webshop.models.Product;
import com.example.webshop.repositories.ProductRepository;
import com.example.webshop.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void removeProduct(Product product) {
        productRepository.delete(product);
    }

    public Product editProductQuantity(Product product, String act) {
        Product editProduct = productRepository.findById(product.getId()).get();
        if ("dec".equalsIgnoreCase(act)) {
            editProduct.setQuantity(product.getQuantity() - 1);
        } else {
            editProduct.setQuantity(product.getQuantity() + 1);
        }
        productRepository.save(editProduct);
        return editProduct;
    }


}
