package it.objectmethod.loobia.mapper;


import org.mapstruct.Mapper;

import it.objectmethod.loobia.dto.MunicipalityDto;
import it.objectmethod.loobia.entity.Municipality;

@Mapper(componentModel = "spring")
public interface MunicipalityMapper extends EntityMapper<MunicipalityDto, Municipality> {

	MunicipalityDto toDto(Municipality m);
}
