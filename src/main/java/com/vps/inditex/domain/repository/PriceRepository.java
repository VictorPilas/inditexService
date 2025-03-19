package com.vps.inditex.domain.repository;

import com.vps.inditex.domain.model.Brand;
import com.vps.inditex.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Integer> {

    @Query("SELECT p FROM Price p WHERE (:requestDate is null or (p.startDate <= :requestDate and p.endDate >= :requestDate)) " +
            "and (:productId is null or p.productId = :productId) " +
            "and (:brandId is null or p.brand = :brandId)  ORDER BY p.priority DESC")
    List<Price> findPriceByFields(@Param("requestDate") Timestamp requestDate,
                                  @Param("productId") Integer productId,
                                  @Param("brandId") Brand brandId);

}
