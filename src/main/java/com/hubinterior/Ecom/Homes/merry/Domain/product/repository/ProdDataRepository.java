package com.hubinterior.Ecom.Homes.merry.Domain.product.repository;

import com.hubinterior.Ecom.Homes.merry.Domain.product.model.ProdData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdDataRepository extends JpaRepository<ProdData, Long> {

}

