package com.hubinterior.Ecom.Homes.merry.Domain.category.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Category")
@Embeddable
public class PrimaryCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="pcatId")
    Long primaryCategoryId;
    @Column(name="pcatName")
    String primaryCategoryName;
    @Column(name="pcatDesc")
    String primaryCategoryDescription;
    @Embedded
    ArrayList<SecondaryCategory> subCategory= new ArrayList<SecondaryCategory>();

}
