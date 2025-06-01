package org.example.onlinestore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Shippings", schema = "dbo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Lob
    @Column(name = "RecipientName", nullable = false)
    private String recipientName;

    @Nationalized
    @Lob
    @Column(name = "DeliveryPoint", nullable = false)
    private String deliveryPoint;

    @Column(name = "Type", nullable = false)
    private Integer type;

    @JsonIgnore
    @OneToMany(mappedBy = "sheppment")
    @JsonBackReference("order-shipping")
    private Set<Order> orders = new LinkedHashSet<>();

}