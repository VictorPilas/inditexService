package com.capitole.inditex.application.mapper;

import com.capitole.inditex.application.dto.PriceDTO;
import com.capitole.inditex.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Mapper(componentModel = "spring",
        uses = {BrandMapper.class})
public interface PriceMapper {

    @Mapping(source = "brand", target = "brandId")
    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "timestampToString")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "timestampToString")
    PriceDTO toDTO(Price entity);

    default Integer toPriceDTO(Price price) {
        if (price == null) {
            return null;
        }
        return price.getId();
    }

    default Price toPriceEntity(Integer priceId) {
        if (priceId == null) {
            return null;
        }
        Price price = new Price();
        price.setId(priceId);
        return price;
    }

    @Named("timestampToString")
    static String timestampToString(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        return dateFormat.format(timestamp);
    }
}
