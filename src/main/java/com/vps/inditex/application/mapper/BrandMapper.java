package com.vps.inditex.application.mapper;

import com.vps.inditex.domain.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

  default Integer toBrandDTO(Brand brand) {
    if (brand == null) {
      return null;
    }
    return brand.getId();
  }

  default Brand toBrandEntity(Integer brandId) {
    if (brandId == null) {
      return null;
    }
    Brand brand = new Brand();
    brand.setId(brandId);
    return brand;
  }


}
