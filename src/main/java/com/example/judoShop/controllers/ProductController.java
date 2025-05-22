package com.example.judoShop.controllers;


import com.example.judoShop.models.Product;
import com.example.judoShop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String products(Model model){
        model.addAttribute("products", productService.getAllProducts()); //Ключ атрибута и значение
        return "products";
    }




}
