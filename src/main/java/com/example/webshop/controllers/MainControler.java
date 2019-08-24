package com.example.webshop.controllers;
import com.example.webshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainControler {
    private ProductService productService;
    @Autowired
    public MainControler(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping({"/","/home"})
    public String getListProduct(HttpServletRequest request){
       // @RequestAttribute("productList") productService.findAll();

        request.setAttribute("productList", productService.findAll());
        request.getSession().setAttribute("name","John");
        System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");


        return "home";
    }
}
