package org.example.onlinestore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.onlinestore.entity.Order;
import org.example.onlinestore.model.Cart;
import org.example.onlinestore.model.SessionCart;
import org.example.onlinestore.service.OrderService;
import org.example.onlinestore.viewmodels.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.example.onlinestore.utils.Utils;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final Utils _utils;

    @Autowired
    public OrderController( OrderService orderService,  Utils utils)
    {
        this.orderService = orderService;
        _utils = utils;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(HttpServletRequest request)
    {
        var cart = SessionCart.getCart(request);
        var mav=new ModelAndView("checkout");
        var order= orderService.createOrderFromCart(cart);
        var orderVM=new OrderViewModel(order);
        mav.addObject("orderVM", orderVM);
        return mav;
    }
    @PostMapping("/checkout")
    public ModelAndView checkout(@ModelAttribute("orderVM") OrderViewModel orderVM, BindingResult bindingResult, RedirectAttributes redirectAttributes,HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        var cart = SessionCart.getCart(request);
        if (cart.getLineCollection().size() == 0) {
            bindingResult.reject("", "Sorry, your cart is empty!");
        }

        var order=orderService.updateOrderWithCart(cart ,orderVM);

        if (!bindingResult.hasErrors()) {
            order.setUserId(UUID.fromString(_utils.getCurrentUserId()));
            orderService.saveOrder(order);
            redirectAttributes.addAttribute("orderNumber", order.getOrderNumber());
            modelAndView.setViewName("redirect:/completed");
        } else {
            modelAndView.addObject("orderVM", orderVM);
            modelAndView.setViewName("viewOrder");
        }

        return modelAndView;
    }
    @GetMapping("/completed")
    public ModelAndView completed(int orderNumber,HttpServletRequest request)
    {
        var mav=new ModelAndView("completed");
        var cart = SessionCart.getCart(request);
        mav.addObject("orderNumber", orderNumber);
        cart.clear();
        return  mav;
    }

    @GetMapping("/orderList")
    public ModelAndView orderList()
    {
        var mav=new ModelAndView("orderList");
        UUID currentUserId = UUID.fromString(_utils.getCurrentUserId());
        var orders = orderService.getOrders(currentUserId);
        mav.addObject("orders", orders);
        return mav;
    }

    @GetMapping("/orderDetails")
    public ModelAndView orderDetails(UUID orderId)
    {
        var order = orderService.getOrderById(orderId);
        var mav=new ModelAndView("orderDetails");
        mav.addObject("order", order);
        return mav;
    }
}
