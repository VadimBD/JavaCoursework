package org.example.onlinestore.controller.api;

import org.example.onlinestore.entity.ProductContainer;
import org.example.onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("productContainerControllerApi")
@RequestMapping("/api/product_containers")
public class ProductContainerController {

    private ProductService productService;
    @Autowired
    public ProductContainerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductContainer getProductContainers(Integer id) {
      return productService.getProductContainer(id);
    }
    @GetMapping
    public List<ProductContainer> getProductContainers() {
        return productService.getAllProductContainers();
    }
}
