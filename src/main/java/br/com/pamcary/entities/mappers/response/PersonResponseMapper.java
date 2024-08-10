package br.com.pamcary.entities.mappers.response;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import br.com.pamcary.entities.PersonEntity;
import br.com.pamcary.entities.dtos.response.PersonResponseDefaultDto;

@Mapper(componentModel = "spring")
public interface PersonResponseMapper {

	PersonResponseMapper INSTANCE = Mappers.getMapper(PersonResponseMapper.class);
	
	PersonResponseDefaultDto toPersonResponseDto(PersonEntity personEntity);
}
