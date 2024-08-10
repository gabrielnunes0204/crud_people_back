package br.com.pamcary.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.pamcary.entities.PersonEntity;
import br.com.pamcary.entities.dtos.request.PersonRequestDto;
import br.com.pamcary.entities.dtos.response.PersonResponseDefaultDto;
import br.com.pamcary.entities.dtos.response.PersonResponseSimpleDto;
import br.com.pamcary.entities.mappers.request.PersonRequestMapper;
import br.com.pamcary.entities.mappers.response.PersonResponseMapper;
import br.com.pamcary.repositories.PersonRepository;
import br.com.pamcary.utils.Utils;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonResponseMapper mapperResponse;
	
	@Autowired
	private PersonRequestMapper mapperRequest;
	
	public List<PersonResponseDefaultDto> getAll() {
		List<PersonResponseDefaultDto> returnDto = new ArrayList<PersonResponseDefaultDto>();
		List<PersonEntity> returnGet = repository.getAll().orElse(new ArrayList<PersonEntity>());
		
		if (!returnGet.isEmpty()) {
			for (PersonEntity item : returnGet) {
				returnDto.add(this.mapperResponse.toPersonResponseDto(item));
			}
		}
		
		return returnDto;
	}
	
	public PersonResponseDefaultDto getByCpf(String cpf) {
		PersonEntity returnGet = repository.getByCpf(cpf).orElse(new PersonEntity());
		return this.mapperResponse.toPersonResponseDto(returnGet);
	}
	
	public PersonResponseSimpleDto insertPerson(PersonRequestDto requestDto) {
		if (!Utils.validateAttributesDto(requestDto)) {
			return new PersonResponseSimpleDto("Há campos inválidos. Inclusão não efetuada.", false);
		}
		
		PersonEntity entity = mapperRequest.toPersonEntity(requestDto);
		this.repository.save(entity);
		
		return new PersonResponseSimpleDto("Inclusão efetuada com sucesso.", true);
	}
	
	public PersonResponseSimpleDto updatePerson(PersonRequestDto requestDto) {
		if (!Utils.validateAttributesDto(requestDto)) {
			return new PersonResponseSimpleDto("Há campos inválidos. Alteração não efetuada.", false);
		}
		
		PersonEntity returnGet = this.repository.getById(requestDto.getId()).orElse(new PersonEntity());
		
		if (returnGet.getId() != null) {
			returnGet.setName(requestDto.getName());
			returnGet.setCpf(requestDto.getCpf());
			returnGet.setDateOfBirth(requestDto.getDateOfBirth());
			this.repository.save(returnGet);
			return new PersonResponseSimpleDto("Alteração efetuada com sucesso.", true);
		}
		
		return new PersonResponseSimpleDto("Pessoa não encontrada. Alteração não efetuada.", false);
	}
	
	public PersonResponseSimpleDto deletePerson(Long id) {
		PersonEntity returnGet = this.repository.getById(id).orElse(new PersonEntity());
		
		if (returnGet.getId() != null) {
			this.repository.delete(returnGet);
			return new PersonResponseSimpleDto("Exclusão efetuada com sucesso.", true);
		}
		
		return new PersonResponseSimpleDto("Pessoa não encontrada. Exclusão não efetuada.", false);
	}
}
