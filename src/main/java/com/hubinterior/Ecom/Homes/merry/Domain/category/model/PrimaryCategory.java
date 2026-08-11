package com.hubinterior.Ecom.Homes.merry.Domain.category.model;

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
@Table(name="Category")
public class PrimaryCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pcatId")
    Long primaryCategoryId;
    @Column(name="pcatName")
    String primaryCategoryName;
    @Column(name="pcatDesc")
    String primaryCategoryDescription;
    @OneToMany(cascade = CascadeType.ALL)
    List<SecondaryCategory> subCategory = new ArrayList<>();

}
