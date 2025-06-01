package org.example.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.annotations.Expose;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.example.onlinestore.entity.Product;

@Getter
@Setter
public class SessionCart extends Cart{
    @JsonIgnore
    public HttpSession session;

    public static  SessionCart getCart(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        ObjectMapper objectMapper = new ObjectMapper();
             String json = (String) session.getAttribute("Cart");
        SessionCart sessionCart = null;
        try {
            sessionCart= (json != null)
                    ? objectMapper.readValue(json, SessionCart.class)
                    : new SessionCart();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            sessionCart= new SessionCart();
        }
        sessionCart.session = session;
        return sessionCart;
    }

    @Override
    public void addItem(Product product,int quantity) {

        super.addItem(product, quantity);
        saveInSesion();
    }

    @Override
    public void  removeLine(Product product) {
        super.removeLine(product);
        saveInSesion();
    }
    @Override
    public void  clear(){
        super.clear();
        saveInSesion();
    }

    private void saveInSesion(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json=mapper.writeValueAsString(this);
            session.setAttribute("Cart", json);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
