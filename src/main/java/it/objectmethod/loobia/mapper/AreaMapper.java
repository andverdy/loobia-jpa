package it.objectmethod.loobia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.loobia.dto.AreaDto;
import it.objectmethod.loobia.entity.Area;

@Mapper(componentModel = "spring")

public interface AreaMapper extends EntityMapper<AreaDto, Area> {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "codzona", target = "codzona")
	@Mapping(source = "user.idutente", target = "id_agente")
	AreaDto toDto(Area area);

}
