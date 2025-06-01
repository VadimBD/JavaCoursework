package org.example.onlinestore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Paymants", schema = "dbo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Paymant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Type", nullable = false)
    private Integer type;

    @Column(name = "Status", nullable = false)
    private Integer status;

    @Column(name = "InsertedDate", nullable = false)
    private LocalDateTime insertedDate;

    @Column(name = "Amount", nullable = false)
    private Float amount;

    @JsonIgnore
    @JsonBackReference("order-paymant")
    @ManyToOne()
    @JoinColumn(name = "OrderId")
    private Order order;
}