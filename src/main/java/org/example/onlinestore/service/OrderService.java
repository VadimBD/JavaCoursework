package org.example.onlinestore.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.onlinestore.entity.Order;
import org.example.onlinestore.entity.Paymant;
import org.example.onlinestore.model.Cart;
import org.example.onlinestore.repository.CartLineRepository;
import org.example.onlinestore.repository.OrderRepository;
import org.example.onlinestore.repository.PaymantRepository;
import org.example.onlinestore.repository.ShippingRepository;
import org.example.onlinestore.viewmodels.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final CartLineRepository cartLineRepository;
    @Autowired
    private final PaymantRepository paymantRepository;
    @Autowired
    private final ShippingRepository shippingRepository;

    public OrderService(OrderRepository orderRepository,
                        CartLineRepository cartLineRepository,
                        PaymantRepository paymantRepository,
                        ShippingRepository shippingRepository) {
        this.orderRepository = orderRepository;
        this.cartLineRepository = cartLineRepository;
        this.paymantRepository = paymantRepository;
        this.shippingRepository = shippingRepository;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public List<Order> getOrders(UUID id){
        return orderRepository.findAll().stream().filter(order -> order.getUserId().equals(id)).collect(Collectors.toList());
    }
    public void deleteOrderById(UUID id) {
        orderRepository.deleteById(id);
    }

    public List<Paymant> getAllPaymants() {
        return paymantRepository.findAll();
    }

    public Order createOrderFromCart(Cart cart){
        Order order = new Order();
        order.setTotal(cart.computeTotalValue());
        order.setCartLines((java.util.Set<org.example.onlinestore.entity.CartLine>) cart.getLineCollection().stream().collect(Collectors.toList()));
        return order;
    }

    public Order updateOrderWithCart(Cart cart, OrderViewModel  orderVM) {
        Order order = orderVM.getOrder();
        order.setCartLines((java.util.Set<org.example.onlinestore.entity.CartLine>) cart.getLineCollection().stream().collect(Collectors.toList()));
        order.setTotal(cart.computeTotalValue());
        return order;
    }
}
