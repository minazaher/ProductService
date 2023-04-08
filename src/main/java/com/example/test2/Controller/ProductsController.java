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
@RequestMapping("/shop/")
public class ProductsController {
    private final RestTemplate restTemplate;
    private final ProductService productService;
    private final ProductRestController productRestController ;
    List<Product> products = new ArrayList<>();

    final String uri = "https://dummyjson.com/products/";
    @GetMapping("/products/{category}/{id}")
    public String getProductCategory(@PathVariable Long id,@PathVariable String category, Model model) {
        Product product = productRestController.getProductById(id);
        category = product.getCategory();
        model.addAttribute("product", product);
        return "single-product";
    }

    @GetMapping("/products/{page}")
    public String getShop(@PathVariable Long page, Model model){
        products = productRestController.getProductsPage(page ,page+1);
        List<String> categories = productService.getCategories();
        List<String> brands = productService.getBrands();

        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "category";
    }

    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam(value = "category", required = false, defaultValue = "all") String category,
                                                        Model model) {
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
    public String getProductsByBrand(@RequestParam(value = "brand", required = false) String brand
                                        ,Model model) {
        List<String> categories = productService.getCategories();
        List<String> brands = productService.getBrands();

        if (brand != null){
            products = productService.getProductsByBrand(brand);
        }
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "category";
    }


}
