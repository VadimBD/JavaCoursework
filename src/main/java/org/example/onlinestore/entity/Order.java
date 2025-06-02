package org.example.onlinestore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Orders", schema = "dbo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "UserId", nullable = false)
    private UUID userId;

    @Column(name = "OrderNumber", nullable = false)
    private Integer orderNumber;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "SheppmentId")
//    @JsonBackReference("order-shipping")
    private Shipping sheppment;

    @Column(name = "Total", nullable = false)
    private Float total;

    @Column(name = "OrderDate")
    private LocalDateTime orderDate;

    @Column(name = "OrderStatus", nullable = false)
    private Integer orderStatus;

    @JsonIgnore
    @JsonBackReference("cartLine-order")
    @OneToMany(mappedBy = "order")
    private Set<CartLine> cartLines = new LinkedHashSet<>();

    @OneToMany(mappedBy = "order")
//    @JsonManagedReference("order-paymant")
    private Set<Paymant> paymants = new LinkedHashSet<>();

}