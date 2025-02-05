package com.capitole.inditex.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Brand brand;

    @Column(name = "START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private Timestamp startDate;

    @Column(name = "END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private Timestamp endDate;

    @Column(name = "PRICE_LIST")
    private Long priceList;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRIORITY")
    private Long priority;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CURR")
    private String curr;

}
