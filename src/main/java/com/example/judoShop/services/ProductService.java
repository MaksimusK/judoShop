package com.example.judoShop.services;

import com.example.judoShop.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private Long ID = 0L;

    {
        products.add(new Product(++ID,"MIZUNO Yusho 3", "Кимоно", "Японское качесвто", 20_000, "Новосибирск", "MIZUNO"));
        products.add(new Product(++ID,"ADIDAS Champion 2", "Кимоно", "Надежное и плотное", 30_000, "Москва","ADIDAS"));
    }

    public List<Product> getAllProducts(){
        return products;
    }

    public void saveProduct(Product product){
        product.setId(++ID);
        products.add(product);
    }

    public void deleteProduct(Long id){
        products.removeIf(product -> product.getId().equals(id));
    }


}
