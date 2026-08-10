package com.hubinterior.Ecom.Homes.merry.Domain.category.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
    ArrayList<SecondaryCategory> subCategory= new ArrayList<SecondaryCategory>();
//    @OneToMany(cascade = CascadeType.ALL)
//    ArrayList<ProdData> Products= new ArrayList<ProdData >();
}
