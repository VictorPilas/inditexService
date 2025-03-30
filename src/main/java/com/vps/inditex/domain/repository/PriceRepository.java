package com.vps.inditex.domain.repository;

import com.vps.inditex.domain.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    @Query("SELECT p FROM PriceEntity p WHERE (:requestDate is null or (p.startDate <= :requestDate and p.endDate >= :requestDate)) " +
            "and (:productId is null or p.productId = :productId) " +
            "and (:brandId is null or p.brandId = :brandId)  ORDER BY p.priority DESC LIMIT 1")
    PriceEntity findPriceByFields(@Param("requestDate") Timestamp requestDate,
                                  @Param("productId") Integer productId,
                                  @Param("brandId") Integer brandId);

}
