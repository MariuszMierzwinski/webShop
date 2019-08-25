package com.example.webshop.controllers;

import com.example.webshop.models.Product;
import com.example.webshop.services.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminControler {
    private ProductService productService;

    @Autowired
    public AdminControler(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String getListProduct(HttpServletRequest request, Model model) {

        model.addAttribute("prduct", new Product());
        System.out.println("");
        request.setAttribute("products", productService.findAll());
        return "th_admin";
    }

    @PostMapping("admin_inc/{id}/{act}")
    public String incQuantityOfProduct(@PathVariable String id, @PathVariable String act) throws NotFoundException {
        productService.editProductQuantity(productService.findById((Long.parseLong(id))), act);
        return "redirect:/admin";
    }

    @RequestMapping(value = "admin_add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/admin";
    }
    @PostMapping("admin_remove/{id}")
    public String removeProduct(@PathVariable String id) throws NotFoundException {
        productService.removeProduct(productService.findById(Long.parseLong(id)));
        return "redirect:/admin";
    }
}


