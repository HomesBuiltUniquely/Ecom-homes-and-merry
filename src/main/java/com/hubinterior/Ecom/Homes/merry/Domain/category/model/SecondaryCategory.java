package com.hubinterior.Ecom.Homes.merry.Domain.category.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class SecondaryCategory {

    Long SecondaryCategoryId;
    String SecondaryCategoryName;
    String SecondaryCategoryDescription;
    ArrayList<SecondaryCategory> subCategory= new ArrayList<SecondaryCategory>();
    ArrayList<ProdData> Products= new ArrayList<ProdData >();
}
