package com.example.test2.Controller;

import com.example.test2.Model.Product;
import com.example.test2.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;
    @GetMapping("/brands")
    public List<String> getAllBrands(){
        return   productService.getBrands();
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productService.getCategories();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductsById(id);
    }



}
