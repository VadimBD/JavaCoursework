package org.example.onlinestore.viewmodels;

import lombok.Getter;
import lombok.Setter;
import org.example.onlinestore.entity.Product;

@Getter
@Setter
public class LendingViewModel {
    public Iterable<Product> topSales;
    public Iterable<Product> noveltys;
}
