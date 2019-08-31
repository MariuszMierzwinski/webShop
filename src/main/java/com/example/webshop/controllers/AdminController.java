package com.example.webshop.controllers;

import com.example.webshop.models.Category;
import com.example.webshop.models.Product;
import com.example.webshop.services.CategoryService;
import com.example.webshop.services.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public AdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/admin")
    public String getListProduct(HttpServletRequest request, Model model) {

        model.addAttribute("prduct", new Product());
        System.out.println("");
        request.setAttribute("products", productService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        return "th_admin";
    }

    @GetMapping("/category")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "th_addcategory";
    }

    @PostMapping("/adminAddCategory")
    public String categoryAdded(@ModelAttribute Category category){
        categoryService.addCategory(category);
        return "redirect:/admin";
    }

    @PostMapping("admin_inc/{id}/{act}")
    public String incQuantityOfProduct(@PathVariable String id, @PathVariable String act) throws NotFoundException {
        productService.editProductQuantity(productService.findById((Long.parseLong(id))), act);
        return "redirect:/admin";
    }

    @RequestMapping(value = "admin_add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute ("product")Product product,@ModelAttribute("cat") String category) {
        product.setCategory(categoryService.getCategory(category));
        System.out.println(categoryService.getCategory(category)+" :: "+ category);
        System.out.println(product);
        productService.addProduct(product);
        return "redirect:/admin";
    }

    @PostMapping("admin_remove/{id}")
    public String removeProduct(@PathVariable String id) throws NotFoundException {
        productService.removeProduct(productService.findById(Long.parseLong(id)));
        return "redirect:/admin";
    }


}


