package com.example.test2.Service;

import com.example.test2.Model.Product;
import com.example.test2.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService{
    private final ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAllProducts();
    }


    public List<Product> getProductsByCategory(String category){
        return repo.findByCategory(category);
    }
    public List<Product> getProductsByBrand(String brand){
        return repo.findByBrand(brand);
    }

    public List<String> getCategories(){
        return repo.findCategories();
    }
    public List<String> getBrands(){
        return repo.findBrands();
    }
    public Product getProductsById(Long id) {
        return repo.findProductById(id);
    }
}
