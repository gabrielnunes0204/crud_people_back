package br.com.pamcary.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.pamcary.controller.PersonController;
import br.com.pamcary.entities.PersonEntity;
import br.com.pamcary.entities.dtos.request.PersonRequestDto;
import br.com.pamcary.entities.dtos.response.PersonResponseDefaultDto;
import br.com.pamcary.entities.dtos.response.PersonResponseSimpleDto;
import br.com.pamcary.entities.mappers.request.PersonRequestMapper;
import br.com.pamcary.entities.mappers.response.PersonResponseMapper;
import br.com.pamcary.repositories.PersonRepository;

public class PersonServiceTest {

	@InjectMocks
    private PersonService service;

	@Mock
	private PersonController controller;
	
    @Mock
    private PersonRepository repository;

    @Mock
    private PersonResponseMapper responseMapper;

    @Mock
    private PersonRequestMapper requestMapper;

    PersonRequestDto requestDto = new PersonRequestDto();
    
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	
    	requestDto.setId(1L);
    	requestDto.setName("Name");
    	requestDto.setCpf("12345678910");
    	requestDto.setDateOfBirth(LocalDate.now());
    }

    @Test
    public void testGetAll() {
        PersonEntity entity = new PersonEntity();
        PersonResponseDefaultDto dto = new PersonResponseDefaultDto();
        List<PersonEntity> entities = new ArrayList<>();
        entities.add(entity);

        when(this.repository.getAll()).thenReturn(Optional.of(entities));
        when(this.responseMapper.toPersonResponseDto(new PersonEntity(1L, "Name", "12345678910", LocalDate.now()))).thenReturn(dto);

        List<PersonResponseDefaultDto> result = this.service.getAll();
        
        assertEquals(1, result.size());
    }
    
    @Test
    public void testGetByCpf() {
        String cpf = "12345678900";
        PersonEntity entity = new PersonEntity();
        PersonResponseDefaultDto dto = new PersonResponseDefaultDto();

        when(this.repository.getByCpf(cpf)).thenReturn(Optional.of(entity));
        when(this.responseMapper.toPersonResponseDto(entity)).thenReturn(dto);

        PersonResponseDefaultDto result = this.service.getByCpf(cpf);

        assertEquals(dto, result);
    }
    
    @Test
    public void testInsertPerson() {
        PersonEntity entity = new PersonEntity();
        PersonResponseSimpleDto response = new PersonResponseSimpleDto("Inclusão efetuada com sucesso.", true);

        when(this.requestMapper.toPersonEntity(this.requestDto)).thenReturn(entity);
        when(this.repository.save(entity)).thenReturn(entity);

        PersonResponseSimpleDto result = this.service.insertPerson(this.requestDto);
        
        assertEquals(response.getMessage(), result.getMessage());
        assertEquals(response.getIsSuccess(), result.getIsSuccess());
    }
    
    @Test
    public void testInsertPersonWithInvalidAttributes() {
        PersonResponseSimpleDto response = new PersonResponseSimpleDto("Há campos inválidos. Ação não efetuada.", false);
        
        this.requestDto.setCpf(null);
        PersonResponseSimpleDto result = this.service.insertPerson(this.requestDto);

        assertEquals(response.getMessage(), result.getMessage());
        assertEquals(response.getIsSuccess(), result.getIsSuccess());
    }
    
    @Test
    public void testUpdatePerson() {
        PersonEntity existingEntity = new PersonEntity();
        existingEntity.setId(1L);
        existingEntity.setName("Name");
        existingEntity.setCpf("12345678910");
        existingEntity.setDateOfBirth(LocalDate.now());
        
        PersonEntity updatedEntity = new PersonEntity();
        
        PersonResponseSimpleDto response = new PersonResponseSimpleDto("Alteração efetuada com sucesso.", true);

        when(this.repository.getById(this.requestDto.getId())).thenReturn(Optional.of(existingEntity));
        when(this.requestMapper.toPersonEntity(this.requestDto)).thenReturn(updatedEntity);
        when(this.repository.save(updatedEntity)).thenReturn(updatedEntity);

        PersonResponseSimpleDto result = this.service.updatePerson(this.requestDto);
        
        assertEquals(response.getMessage(), result.getMessage());
        assertEquals(response.getIsSuccess(), result.getIsSuccess());
    }
    
    @Test
    public void testUpdatePersonWithErrorSearch() {
        PersonEntity existingEntity = new PersonEntity();
        existingEntity.setId(1L);
        existingEntity.setName("Name");
        existingEntity.setCpf("12345678910");
        existingEntity.setDateOfBirth(LocalDate.now());
        
        PersonEntity updatedEntity = new PersonEntity();
        
        PersonResponseSimpleDto response = new PersonResponseSimpleDto("Pessoa não encontrada. Ação não efetuada.", false);

        when(this.repository.getByCpf(this.requestDto.getCpf())).thenReturn(Optional.of(existingEntity));
        when(this.requestMapper.toPersonEntity(this.requestDto)).thenReturn(updatedEntity);
        when(this.repository.save(updatedEntity)).thenReturn(updatedEntity);

        PersonResponseSimpleDto result = this.service.updatePerson(this.requestDto);
        
        assertEquals(response.getMessage(), result.getMessage());
        assertEquals(response.getIsSuccess(), result.getIsSuccess());
    }
    
    @Test
    public void testDeletePerson() {
        Long id = 1L;
        PersonResponseSimpleDto response = new PersonResponseSimpleDto("Exclusão efetuada com sucesso.", true);

        when(this.repository.getById(id)).thenReturn(Optional.of(new PersonEntity(1L, "Name", "12345678910", LocalDate.now())));
        doNothing().when(this.repository).delete(new PersonEntity(1L, "Name", "12345678910", LocalDate.now()));

        PersonResponseSimpleDto serviceResponse = this.service.deletePerson(id);
        
        assertEquals(response.getMessage(), serviceResponse.getMessage());
        assertEquals(response.getIsSuccess(), serviceResponse.getIsSuccess());
    }
    
    @Test
    public void testDeletePersonWithError() {
        Long id = 1L;
        PersonResponseSimpleDto response = new PersonResponseSimpleDto("Pessoa não encontrada. Ação não efetuada.", false);

        when(this.repository.getByCpf(this.requestDto.getCpf())).thenReturn(Optional.of(new PersonEntity(1L, "Name", "12345678910", LocalDate.now())));
        doNothing().when(this.repository).delete(new PersonEntity(1L, "Name", "12345678910", LocalDate.now()));

        PersonResponseSimpleDto serviceResponse = this.service.deletePerson(id);
        
        assertEquals(response.getMessage(), serviceResponse.getMessage());
        assertEquals(response.getIsSuccess(), serviceResponse.getIsSuccess());
    }
    
    @Test
    public void testUpdatePersonWithError() {
    	PersonEntity existingEntity = new PersonEntity();
        PersonEntity updatedEntity = new PersonEntity();
        PersonResponseSimpleDto response = new PersonResponseSimpleDto("Há campos inválidos. Ação não efetuada.", false);

        when(this.repository.getByCpf(this.requestDto.getCpf())).thenReturn(Optional.of(existingEntity));
        when(this.requestMapper.toPersonEntity(this.requestDto)).thenReturn(updatedEntity);
        when(this.repository.save(updatedEntity)).thenReturn(updatedEntity);

        this.requestDto.setName(null);
        PersonResponseSimpleDto result = this.service.updatePerson(this.requestDto);

        assertEquals(response.getMessage(), result.getMessage());
        assertEquals(response.getIsSuccess(), result.getIsSuccess());
    }
}