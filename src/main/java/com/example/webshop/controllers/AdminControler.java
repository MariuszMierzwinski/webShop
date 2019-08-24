package com.example.webshop.controllers;

import com.example.webshop.models.Product;
import com.example.webshop.models.User;
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
    @PostMapping("admin_inc/{id}")
    public String incQuantityOfProduct(@PathVariable String id) throws NotFoundException {
       productService.editProductQuantity(productService.findById((Long.parseLong(id))),"as");
        System.out.println(id);
       return "redirect:/admin";
    }
    @RequestMapping(value = "admin_add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute Product product){
        productService.addProduct(product);
        return "redirect:admin";
    }
   /* public String createNewProductFromRequest(HttpServletRequest req) {
        System.out.println("POSTPOSTPOSTPOSTPOSTPOSTPOSTPOSTPOST");
        String size = req.getParameter("size");
        System.out.println(size);
        String name = req.getParameter("name");
        System.out.println(name);
        String colour = req.getParameter("colour");
        String sex = req.getParameter("sex");
        double price = Double.parseDouble(req.getParameter("price"));
        long quantity = Long.parseLong(req.getParameter("quantity"));
        new Product(size, name, colour, sex, price, quantity, 0);
        return "adminView";*/
    }


