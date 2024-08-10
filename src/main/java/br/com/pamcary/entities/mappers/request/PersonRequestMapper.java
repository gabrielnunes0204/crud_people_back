package br.com.pamcary.entities.mappers.request;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import br.com.pamcary.entities.PersonEntity;
import br.com.pamcary.entities.dtos.request.PersonRequestDto;

@Mapper(componentModel = "spring")
public interface PersonRequestMapper {

	PersonRequestMapper INSTANCE = Mappers.getMapper(PersonRequestMapper.class);
	
	PersonEntity toPersonEntity(PersonRequestDto requestDt);
}
