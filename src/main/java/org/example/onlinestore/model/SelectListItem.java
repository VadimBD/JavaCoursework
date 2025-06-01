package org.example.onlinestore.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectListItem {
    private String value;
    private String text;

    public SelectListItem(String value, String text) {
        this.value = value;
        this.text = text;
    }
}