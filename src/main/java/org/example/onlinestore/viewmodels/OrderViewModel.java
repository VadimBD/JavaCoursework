package org.example.onlinestore.viewmodels;

import lombok.Getter;
import org.example.onlinestore.entity.CartLine;
import org.example.onlinestore.entity.Order;
import org.example.onlinestore.enums.ShippingType;
import org.example.onlinestore.model.SelectListItem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderViewModel {
    public Order order ;
    public List<CartLine> cartLines;
    public Iterable<SelectListItem> shippingTypeSLI ;
    public OrderViewModel(Order order) {
        this.order = order;
    }
    public List<SelectListItem> getShippingTypeSLI() {
        return Arrays.stream(ShippingType.values())
                .map(i -> new SelectListItem(String.valueOf(i.getValue()), i.name()))
                .collect(Collectors.toList());
    }
}