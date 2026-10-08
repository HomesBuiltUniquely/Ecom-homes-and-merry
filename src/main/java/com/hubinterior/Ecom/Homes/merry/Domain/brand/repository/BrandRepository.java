package com.hubinterior.Ecom.Homes.merry.Domain.brand.repository;

import com.hubinterior.Ecom.Homes.merry.Domain.brand.enums.BrandStatus;
import com.hubinterior.Ecom.Homes.merry.Domain.brand.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByCode(String code);

    Optional<Brand> findByCode(String code);

    List<Brand> findByStatus(BrandStatus status);

    @Query("""
        SELECT b FROM Brand b
        WHERE (:search IS NULL OR :search = ''
               OR LOWER(b.brandName) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(b.code) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(b.manufacturer) LIKE LOWER(CONCAT('%', :search, '%')))
          AND (:status IS NULL OR b.status = :status)
          AND (:country IS NULL OR :country = '' OR LOWER(b.country) = LOWER(:country))
    """)
    Page<Brand> findAllWithFilters(
            @Param("search") String search,
            @Param("status") BrandStatus status,
            @Param("country") String country,
            Pageable pageable
    );

    long countByStatus(BrandStatus status);

    @Query("SELECT COUNT(DISTINCT b.country) FROM Brand b WHERE b.country IS NOT NULL AND TRIM(b.country) != ''")
    long countDistinctCountries();
}
