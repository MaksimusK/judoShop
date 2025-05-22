package com.example.judoShop.controllers;


import com.example.judoShop.models.Product;
import com.example.judoShop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String products(Model model){
        model.addAttribute("products", productService.getAllProducts()); //Ключ атрибута и значение
        return "products";
    }

    @PostMapping("product/create") //Сразу получает объект
    public String createProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/";
    }




}
