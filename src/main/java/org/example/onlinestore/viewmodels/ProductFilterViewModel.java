package org.example.onlinestore.viewmodels;

import lombok.Getter;
import lombok.Setter;
import org.example.onlinestore.entity.Brand;
import org.example.onlinestore.enums.*;


import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ProductFilterViewModel {
    private List<Brand> brands  = Collections.emptyList();
    private List<Integer> selectedBrands=Collections.emptyList() ;
    private Gender selectedGender;
    private List<ProductType> selectedProductTypes =Collections.emptyList();
}
