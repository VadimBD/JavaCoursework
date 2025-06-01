package org.example.onlinestore.controller.api;

import org.example.onlinestore.entity.Brand;
import org.example.onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/brands")
@RestController("brandControllerApi")
public class BrandController {
    private ProductService productService;
    @Autowired
    public BrandController(ProductService productService ) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public Brand getBrand(@RequestParam Integer id) {
        return productService.getBrand(id);
    }
    @GetMapping
    public List<Brand> getAllBrands() {
        return productService.getAllBrands();
    }

}
