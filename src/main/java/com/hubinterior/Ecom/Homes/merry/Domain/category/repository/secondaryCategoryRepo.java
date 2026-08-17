package com.hubinterior.Ecom.Homes.merry.Domain.category.repository;

import com.hubinterior.Ecom.Homes.merry.Domain.category.model.SecondaryCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface secondaryCategoryRepo extends JpaRepository<SecondaryCategory, Long> {

    List<SecondaryCategory> findByPrimaryCategoryPrimaryCategoryIdAndParentIsNull(Long primaryCategoryId);
}
