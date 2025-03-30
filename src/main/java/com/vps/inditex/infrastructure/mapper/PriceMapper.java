package com.vps.inditex.infrastructure.mapper;

import com.vps.inditex.domain.model.Price;
import com.vps.inditex.domain.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "timestampToString")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "timestampToString")
    Price toDTO(PriceEntity entity);

    default Integer toPriceDTO(PriceEntity priceEntity) {
        if (priceEntity == null) {
            return null;
        }
        return priceEntity.getId();
    }

    default PriceEntity toPriceEntity(Integer priceId) {
        if (priceId == null) {
            return null;
        }
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(priceId);
        return priceEntity;
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
