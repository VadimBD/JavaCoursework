package org.example.onlinestore.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.onlinestore.entity.Order;
import org.example.onlinestore.entity.Paymant;
import org.example.onlinestore.enums.OrderStatuses;
import org.example.onlinestore.model.Cart;
import org.example.onlinestore.repository.CartLineRepository;
import org.example.onlinestore.repository.OrderRepository;
import org.example.onlinestore.repository.PaymantRepository;
import org.example.onlinestore.repository.ShippingRepository;
import org.example.onlinestore.viewmodels.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
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
        shippingRepository.save(order.getSheppment());
        LocalDateTime now = LocalDateTime.now();
        var maxNum =orderRepository.findMaxOrderNumber();
        if (maxNum==null)
            order.setOrderNumber(1);
        else
            order.setOrderNumber(maxNum+1);
        order.setOrderStatus(OrderStatuses.NEW.getValue());
        order.setOrderDate(now);

        orderRepository.save(order);
    }



    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public List<Order> getOrders(UUID id){
        var list=orderRepository.findAll().stream().filter(order -> order.getUserId().equals(id)).collect(Collectors.toList());
        return list;
    }
    public void deleteOrderById(UUID id) {
        orderRepository.deleteById(id);
    }

    public List<Paymant> getAllPaymants() {
        return paymantRepository.findAll();
    }

    public Order createNewOrder(Cart cart){
        Order order = new Order();
        order.setTotal(cart.computeTotalValue());
        order.setCartLines( new LinkedHashSet<>(cart.getLineCollection()));
        return order;
    }
}
