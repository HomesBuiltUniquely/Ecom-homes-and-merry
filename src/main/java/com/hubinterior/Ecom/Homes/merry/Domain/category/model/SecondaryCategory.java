package com.hubinterior.Ecom.Homes.merry.Domain.category.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class SecondaryCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="scatId")
    Long SecondaryCategoryId;
    @Column(name="scatName")
    String SecondaryCategoryName;
    @Column(name="scatDesc")
    String SecondaryCategoryDescription;
    @OneToMany(cascade = CascadeType.ALL)
    List<SecondaryCategory> subCategory = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    List<ProdData> Products = new ArrayList<>();
}
