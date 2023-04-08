package com.example.test2.Repository;

import com.example.test2.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


//    @Query("SELECT p FROM products  WHERE p.category = :categoryName")
    Product findProductById(@Param(("id")) Long id);
    List<Product> findByCategory(@Param("categoryName") String categoryName);
    List<Product> findByBrand(@Param("brandName") String brandName);
    @Query("SELECT p FROM products p")
    List<Product> findAllProducts();
    @Query(value = "Select DISTINCT category from products")
    List<String> findCategories();
    @Query("Select DISTINCT brand from products")
    List<String> findBrands();
    @Query("select p from products p where p.id < 10 * :page and p.id > 10 * :i")
    List<Product> findProductsPage(long i ,Long page);
}
