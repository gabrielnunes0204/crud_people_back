package br.com.pamcary.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.pamcary.entities.dtos.request.PersonRequestDto;
import br.com.pamcary.entities.dtos.response.PersonResponseDefaultDto;
import br.com.pamcary.entities.dtos.response.PersonResponseSimpleDto;
import br.com.pamcary.services.PersonService;

public class PersonControllerTest {

	@InjectMocks
    private PersonController controller;

    @Mock
    private PersonService service;

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
        PersonResponseDefaultDto response = new PersonResponseDefaultDto();
        when(this.service.getAll()).thenReturn(Collections.singletonList(response));

        ResponseEntity<List<PersonResponseDefaultDto>> responseEntity = this.controller.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }
    
    @Test
    public void testGetByCpf() {
        String cpf = "12345678900";
        PersonResponseDefaultDto response = new PersonResponseDefaultDto();
        when(this.service.getByCpf(cpf)).thenReturn(response);

        ResponseEntity<PersonResponseDefaultDto> responseEntity = this.controller.getByCpf(cpf);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
    }
    
    @Test
    public void testInsertPerson() {
        PersonResponseSimpleDto response = new PersonResponseSimpleDto("Inclusão efetuada com sucesso.", true);
        when(this.service.insertPerson(new PersonRequestDto(1L, "Name", "12345678910", LocalDate.now()))).thenReturn(response);

        ResponseEntity<PersonResponseSimpleDto> responseEntity = this.controller.insertPerson(this.requestDto);
        
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    
    @Test
    public void testUpdatePerson() {
        PersonResponseSimpleDto response = new PersonResponseSimpleDto("Alteração efetuada com sucesso.", true);
        when(this.service.updatePerson(new PersonRequestDto(1L, "Name", "12345678910", LocalDate.now()))).thenReturn(response);

        ResponseEntity<PersonResponseSimpleDto> responseEntity = this.controller.updatePerson(this.requestDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testDeletePerson() {
        Long id = 1L;
        PersonResponseSimpleDto response = new PersonResponseSimpleDto("Exclusão efetuada com sucesso.", true);
        when(this.service.deletePerson(1L)).thenReturn(response);

        ResponseEntity<PersonResponseSimpleDto> responseEntity = this.controller.deletePerson(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
