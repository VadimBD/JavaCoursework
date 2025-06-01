package org.example.onlinestore.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.onlinestore.model.Cart;

@Getter
@Setter
@AllArgsConstructor
public class CartIndexViewModel {
    public Cart cart;
    public String returnUrl="";
}
