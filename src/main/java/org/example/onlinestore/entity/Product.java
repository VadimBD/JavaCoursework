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
@Table(name = "Products", schema = "dbo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Lob
    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Price", nullable = false)
    private Float price;

    @Nationalized
    @Lob
    @Column(name = "Description", nullable = false)
    private String description;

    @Nationalized
    @Lob
    @Column(name = "Article", nullable = false)
    private String article;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BrandId")
//    @JsonManagedReference("product-brand")
    private Brand brand;
    @ManyToOne()
    @JoinColumn(name = "ContainerId")
//    @JsonManagedReference("product-container")
    private ProductContainer container;

    @Column(name = "ProductType", nullable = false)
    private Integer productType;

    @Column(name = "Gender", nullable = false)
    private Integer gender;

    @Column(name = "TopSales", nullable = false)
    private Boolean topSales = false;

    @Column(name = "Novelty", nullable = false)
    private Boolean novelty = false;

    @Nationalized
    @Lob
    @Column(name = "Image", nullable = false)
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    @JsonBackReference("cartLine-product")
    private Set<CartLine> cartLines = new LinkedHashSet<>();

//    @JsonManagedReference("product-review")
    @OneToMany(mappedBy = "product")
    private Set<Review> reviews = new LinkedHashSet<>();

}