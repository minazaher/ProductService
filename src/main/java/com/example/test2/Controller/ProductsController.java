package com.example.test2.Controller;

import com.example.test2.Model.Product;
import com.example.test2.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/")
public class ProductsController {
    private final RestTemplate restTemplate;
    private final ProductService productService;
    private final ProductRestController productRestController ;

    final String uri = "https://dummyjson.com/products/";
    @GetMapping("/products/{id}")
    public String getProductPage(@PathVariable Long id, Model model) {
        Product product = productRestController.getProductById(id);
        model.addAttribute("product", product);
        return "single-product";
    }


    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam(value = "category", required = false, defaultValue = "all") String category,
                                                        Model model) {
        List<Product> products = new ArrayList<>();
        List<String> categories = productService.getCategories();
        List<String> brands = productService.getBrands();
        if (category.equals("all"))
            products = productService.getAllProducts();
        else
            products = productService.getProductsByCategory(category);

        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "category";
    }


    @GetMapping("/product")
    public String getProductsByBrand(@RequestParam(value = "brand", required = false, defaultValue = "all") String brand
                                        ,Model model) {
        List<Product> products = new ArrayList<>();
        List<String> categories = productService.getCategories();
        List<String> brands = productService.getBrands();
        if (brands.equals("all"))
            products = productService.getAllProducts();
        else
            products = productService.getProductsByBrand(brand);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "category";
    }


    //    @GetMapping("/products")
//    public String getProducts(Model model) {
//        List<Product> products = new ArrayList<>();
//        ArrayList<String> categories = new ArrayList<>();
//        for (int i = 1; i < 20; i++) {
//            Product product = restTemplate.getForObject(uri+i , Product.class);
//            products.add(product);
//            if (!categories.contains(product.getCategory())){
//                categories.add(product.getCategory());
//            }
//        }
//        model.addAttribute("categories", categories);
//        model.addAttribute("products", products);
//        return "category";
//    }


}
