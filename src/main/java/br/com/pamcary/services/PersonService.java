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

	private static final String PESSOA_NAO_ENCONTRADA = "Pessoa não encontrada. Ação não efetuada.";
	private static final String CPF_JA_EXISTENTE = "CPF já existente. Ação não efetuada.";
	private static final String CAMPOS_INVALIDOS = "Há campos inválidos. Ação não efetuada.";

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
			return new PersonResponseSimpleDto(CAMPOS_INVALIDOS, false);
		}
		
		if (this.validateCpfExists(requestDto.getCpf(), requestDto.getId())) {
			return new PersonResponseSimpleDto(CPF_JA_EXISTENTE, false);
		}
		
		PersonEntity entity = mapperRequest.toPersonEntity(requestDto);
		this.repository.save(entity);
		
		return new PersonResponseSimpleDto("Inclusão efetuada com sucesso.", true);
	}
	
	public PersonResponseSimpleDto updatePerson(PersonRequestDto requestDto) {
		if (!Utils.validateAttributesDto(requestDto)) {
			return new PersonResponseSimpleDto(CAMPOS_INVALIDOS, false);
		}
		
		if (this.validateCpfExists(requestDto.getCpf(), requestDto.getId())) {
			return new PersonResponseSimpleDto(CPF_JA_EXISTENTE, false);
		}
		
		PersonEntity returnGet = this.repository.getById(requestDto.getId()).orElse(new PersonEntity());
		
		if (returnGet.getId() != null) {
			returnGet.setName(requestDto.getName());
			returnGet.setCpf(requestDto.getCpf());
			returnGet.setDateOfBirth(requestDto.getDateOfBirth());
			this.repository.save(returnGet);
			return new PersonResponseSimpleDto("Alteração efetuada com sucesso.", true);
		}
		
		return new PersonResponseSimpleDto(PESSOA_NAO_ENCONTRADA, false);
	}
	
	public PersonResponseSimpleDto deletePerson(Long id) {
		PersonEntity returnGet = this.repository.getById(id).orElse(new PersonEntity());
		
		if (returnGet.getId() != null) {
			this.repository.delete(returnGet);
			return new PersonResponseSimpleDto("Exclusão efetuada com sucesso.", true);
		}
		
		return new PersonResponseSimpleDto(PESSOA_NAO_ENCONTRADA, false);
	}
	
	private boolean validateCpfExists(String cpf, Long id) {
		List<PersonResponseDefaultDto> retornoGet = this.getAll();
		
		for (PersonResponseDefaultDto item : retornoGet) {
			if (cpf.equals(item.getCpf()) && id != item.getId()) {
				return true;
			}
		}
		
		return false;
	}
}