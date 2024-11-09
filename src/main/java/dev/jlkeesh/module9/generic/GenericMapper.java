package dev.jlkeesh.module9.generic;

import dev.jlkeesh.module9.product.Product;
import dev.jlkeesh.module9.product.ProductUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;


public interface GenericMapper<E, D, CD, UD> {
    E toEntity(CD dto);

    D toDto(E entity);

    List<D> toDto(List<E> entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E partialUpdate(UD dto, @MappingTarget E entity);
}
