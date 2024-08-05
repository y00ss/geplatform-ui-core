package com.geplatform.geviews.mapper;

import com.geplatform.geviews.data.anagrafica.Anagrafica;
import com.geplatform.geviews.dto.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company toDTO(Anagrafica anagrafica);
    Anagrafica toEntity(Company company);
}