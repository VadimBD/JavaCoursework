package org.example.onlinestore.viewmodels;

import lombok.Getter;
import lombok.Setter;
import org.example.onlinestore.entity.Product;
import org.example.onlinestore.enums.*;


import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ProductListViewModel {
    public Iterable<Product> products;
    public List<Integer> brandsId= Collections.emptyList();
    public List<ProductType> productTypes=Collections.emptyList();
    public Gender gender;
    public PagingInfoVM pagingInfo;
}
