package com.hubinterior.Ecom.Homes.merry.Domain.category.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class PrimaryCategory {

    Long PrimaryCategoryId;
    String PrimaryCategoryName;
    String PrimaryCategoryDescription;
    ArrayList<SecondaryCategory> subCategory= new ArrayList<SecondaryCategory>();

}
