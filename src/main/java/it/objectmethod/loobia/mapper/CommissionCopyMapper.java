package it.objectmethod.loobia.mapper;

import org.mapstruct.Mapper;

import it.objectmethod.loobia.dto.CommissionCopyDto;
import it.objectmethod.loobia.entity.CommissionCopy;

@Mapper(componentModel = "spring")
public interface CommissionCopyMapper extends EntityMapper<CommissionCopyDto, CommissionCopy> {

	CommissionCopyDto toDto(CommissionCopy entity);
}
