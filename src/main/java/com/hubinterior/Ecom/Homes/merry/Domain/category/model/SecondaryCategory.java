package com.hubinterior.Ecom.Homes.merry.Domain.category.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Embeddable
public class SecondaryCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="scatId")
    Long SecondaryCategoryId;
    @Column(name="scatId")
    String SecondaryCategoryName;
    @Column(name="scatId")
    String SecondaryCategoryDescription;
    @Embedded
    ArrayList<SecondaryCategory> subCategory= new ArrayList<SecondaryCategory>();
    @Embedded
    ArrayList<ProdData> Products= new ArrayList<ProdData >();
}
