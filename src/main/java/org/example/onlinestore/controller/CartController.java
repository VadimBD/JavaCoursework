package org.example.onlinestore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.onlinestore.entity.Product;
import org.example.onlinestore.model.Cart;
import org.example.onlinestore.model.SessionCart;
import org.example.onlinestore.service.ProductService;
import org.example.onlinestore.viewmodels.CartIndexViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ProductService productService;

        @Autowired
    public CartController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/cartIndex")
    public ModelAndView cartIndex(String returnUrl,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("cartIndex");
        var cartIndexVM= new CartIndexViewModel(SessionCart.getCart(request),returnUrl);
        mav.addObject(cartIndexVM);
        return mav;
    }
    @PostMapping(value = "/addToCart")
    public String addToCart(@RequestParam("id") int id, @RequestParam("returnUrl") String returnUrl, HttpServletRequest request) {
        Product product = productService.getProduct(id);
        var cart = SessionCart.getCart(request);
        if (product != null) {
            cart.addItem(product, 1);
        }
        return String.format("redirect:%s", returnUrl);
    }
    @PostMapping("/remove")
    public String RemoveFromCart(@RequestParam("id") int id, HttpServletRequest request) {
        Product product = productService.getProduct(id);
        var cart=SessionCart.getCart(request);
        if (product != null)
        {
            cart.removeLine(product);
        }
        return"redirect:/cart/cartIndex";
    }
}