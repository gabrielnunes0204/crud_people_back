package br.com.pamcary.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.pamcary.entities.dtos.request.PersonRequestDto;
import br.com.pamcary.entities.dtos.response.PersonResponseDefaultDto;
import br.com.pamcary.entities.dtos.response.PersonResponseSimpleDto;
import br.com.pamcary.services.PersonService;

@RestController
@RequestMapping(value = "/people")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@GetMapping
	public ResponseEntity<List<PersonResponseDefaultDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.service.getAll());
	}
	
	@GetMapping(value = "/{cpf}")
	public ResponseEntity<PersonResponseDefaultDto> getByCpf(@PathVariable String cpf) {
		return ResponseEntity.status(HttpStatus.OK).body(this.service.getByCpf(cpf));
	}
	
	@PostMapping
	public ResponseEntity<PersonResponseSimpleDto> insertPerson(@RequestBody PersonRequestDto requestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.insertPerson(requestDto));
	}
	
	@PutMapping
	public ResponseEntity<PersonResponseSimpleDto> updatePerson(@RequestBody PersonRequestDto requestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(this.service.updatePerson(requestDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PersonResponseSimpleDto> deletePerson(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.service.deletePerson(id));
	}
}
