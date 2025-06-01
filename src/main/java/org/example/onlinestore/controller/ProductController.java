package org.example.onlinestore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.onlinestore.entity.Brand;
import org.example.onlinestore.viewmodels.PagingInfoVM;
import org.example.onlinestore.viewmodels.ProductListViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.example.onlinestore.utils.Utils;
import org.example.onlinestore.service.ProductService;
import org.example.onlinestore.entity.Product;
import org.example.onlinestore.enums.ProductType;
import org.example.onlinestore.enums.Gender;



import java.util.List;
import java.util.stream.Collectors;
@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private int pageSize;
    Utils utils;
    @Autowired
    public ProductController(ProductService productService , Utils utils) {
        pageSize=20;
        this.productService=productService;
        this.utils=utils;
    }
    public List<Product> getFilteredProducts(List<Integer> brand, List<ProductType> type, Gender gender, int page, int pageSize) {
        List<Product> allProducts = (List<Product>) productService.getAllProducts();

        return allProducts.stream()
                .filter(p ->
                        (type == null || type.isEmpty() || type.contains(p.getProductType())) &&
                                (brand == null || brand.isEmpty() || brand.contains(p.getBrand().getId())) &&
                                (gender == null || p.getGender().equals(gender.getCode())))
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

    }
    @GetMapping("/products")
    public ModelAndView listProducts(@RequestParam(required = false) List<Integer> brand,
                                     @RequestParam(required = false) List<ProductType> type,
                                     @RequestParam(required = false) Gender gender,
                                     @RequestParam(defaultValue = "1") int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);

        List<Product> productList = getFilteredProducts(brand, type, gender, page, pageSize);

        ProductListViewModel productsVM = new ProductListViewModel();
        productsVM.setProducts(productList);
        productsVM.setPagingInfo(new PagingInfoVM(page, pageSize, productList.size()));
        productsVM.setGender(gender);
        productsVM.setBrandsId(brand);
        productsVM.setProductTypes(type);

        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("productsVM", productsVM);
        return modelAndView;
    }

    @GetMapping("/products/{id}")
    public ModelAndView showProduct(@PathVariable Integer id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("aboutProduct");
        var product = productService.getProduct(id);

        modelAndView.addObject("product", product);
        modelAndView.addObject("returnUrl", request.getRequestURI());

        return modelAndView;
    }


    @GetMapping("/brands/{id}")
    public ModelAndView brandInfo(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("brandInfo");
        var brand= productService.getBrand(id);
        mav.addObject("brand", brand);
        return mav;
    }

    @GetMapping("/brands")
    public ModelAndView allBrands() {
        ModelAndView mav = new ModelAndView("brands");
        Iterable<Brand> brands = productService.getAllBrands();
        mav.addObject("brands", brands);
        return mav;
    }
}
