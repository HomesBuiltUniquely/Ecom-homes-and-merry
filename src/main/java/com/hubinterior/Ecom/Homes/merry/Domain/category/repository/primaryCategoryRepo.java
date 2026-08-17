package com.hubinterior.Ecom.Homes.merry.Domain.category.repository;

import com.hubinterior.Ecom.Homes.merry.Domain.category.model.PrimaryCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface primaryCategoryRepo extends JpaRepository<PrimaryCategory,Long> {
}
