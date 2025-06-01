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
@Table(name = "\"Brands\"", schema = "dbo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"Id\"")
    private Integer id;

    @Nationalized
    @Lob
    @Column(name = "\"Name\"")
    private String name;

    @Nationalized
    @Lob
    @Column(name = "\"DisplayName\"")
    private String displayName;

    @Nationalized
    @Lob
    @Column(name = "\"Discription\"")
    private String discription;

    @Nationalized
    @Lob
    @Column(name = "\"Image\"")
    private String image;

    @JsonIgnore
    @JsonBackReference("product-brand")
    @OneToMany(mappedBy = "brand")
    private Set<Product> products = new LinkedHashSet<>();
}
