package org.example.onlinestore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.onlinestore.entity.Order;
import org.example.onlinestore.model.SessionCart;
import org.example.onlinestore.service.OrderService;
import org.example.onlinestore.viewmodels.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.example.onlinestore.utils.Utils;

import java.util.LinkedHashSet;
import java.util.UUID;

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
        var order= orderService.createNewOrder(cart);
        var orderVM=new OrderViewModel(order);
        orderVM.cartLines=cart.getLineCollection();
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

        Order order = orderVM.getOrder();
        order.setCartLines(new LinkedHashSet<>(cart.getLineCollection()));
        order.setTotal(cart.computeTotalValue());

        if (!bindingResult.hasErrors()) {
            order.setUserId(UUID.fromString(_utils.getCurrentUserId(request)));
            orderService.saveOrder(order);
//            redirectAttributes.addAttribute("orderNumber", order.getOrderNumber());
            modelAndView.setViewName(String.format("redirect:/order/completed/%s",order.getOrderNumber()) );
        } else {
            modelAndView.addObject("orderVM", orderVM);
            modelAndView.setViewName("viewOrder");
        }

        return modelAndView;
    }
    @GetMapping("/completed/{orderNumber}")
    public ModelAndView completed(@PathVariable int orderNumber, HttpServletRequest request)
    {
        var mav=new ModelAndView("completed");
        var cart = SessionCart.getCart(request);
        mav.addObject("orderNumber", orderNumber);
        cart.clear();
        return  mav;
    }

    @GetMapping("/orderList")
    public ModelAndView orderList(HttpServletRequest request)
    {
        var mav=new ModelAndView("orderList");
        UUID currentUserId = UUID.fromString(_utils.getCurrentUserId(request));
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
