package org.example.onlinestore.controller.api;

import org.example.onlinestore.entity.Brand;
import org.example.onlinestore.entity.Product;
import org.example.onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("productControllerApi")
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService ) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct (@RequestParam Integer id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<Product> getProducts () {
        return productService.getAllProducts();
    }


}
