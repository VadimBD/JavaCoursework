package org.example.onlinestore.model;

import lombok.Getter;
import org.example.onlinestore.entity.CartLine;
import org.example.onlinestore.entity.Product;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Cart {
    private List<CartLine> lineCollection = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        CartLine line = lineCollection.stream()
                .filter(p -> p.getProduct().getId()==product.getId())
                .findFirst()
                .orElse(null);

        if (line == null) {
            var tmpCartLine=new CartLine();
            tmpCartLine.setProduct(product);
            tmpCartLine.setQuantity(quantity);
            lineCollection.add(tmpCartLine);
        } else {
            line.setQuantity(line.getQuantity() + quantity);
        }
    }

    public void removeLine(Product product) {
        lineCollection.removeIf(line -> line.getProduct().getId() == product.getId());
    }
    public  float computeTotalValue(){
        return (float) lineCollection.stream().mapToDouble(line->line.getProduct().getPrice()*line.getQuantity()).sum();
    }

    public  void clear()
    {
        lineCollection.clear();
    }
}
