package com.example.webshop.controllers;


import com.example.webshop.models.Product;
import com.example.webshop.services.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    private ProductService productService;
    @Autowired
    public UploadController(ProductService productService) {
        this.productService = productService;
    }

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/home/mariusz/Documents/webshop/src/main/webapp/Picture/";


    @PostMapping("/upload")
    public String singleFileUpload(@ModelAttribute("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,@ModelAttribute("product_id") String id) {
        System.out.println("file: "+file.getOriginalFilename());
        System.out.println("id: "+id);

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:admin";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println("ok" + file.getOriginalFilename()+" Pro "+path.getFileName().toString());
            Product editedProduct = productService.findById(Long.parseLong(id));
            editedProduct.setPicture("/Picture/"+path.getFileName().toString());
            productService.addProduct(editedProduct);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }

        return "redirect:/admin";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}