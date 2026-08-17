package com.hubinterior.Ecom.Homes.merry.Domain.product.repository;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdDataRepository extends JpaRepository<ProdData, Long> {

    @Query("SELECT COUNT(p) > 0 FROM ProdData p WHERE p.sku_id = :sku_id")
    boolean existsBySku_id(@Param("sku_id") String sku_id);

    @Query("SELECT p FROM ProdData p WHERE p.sku_id = :sku_id")
    Optional<ProdData> findBySku_id(@Param("sku_id") String sku_id);
}
