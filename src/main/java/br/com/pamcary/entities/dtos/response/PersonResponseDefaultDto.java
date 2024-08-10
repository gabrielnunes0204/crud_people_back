package br.com.pamcary.entities.dtos.response;

import java.time.LocalDate;

public class PersonResponseDefaultDto {

	private Long id;
	private String name;
	private String cpf;
	private LocalDate dateOfBirth;

	public PersonResponseDefaultDto() {}
	
	public PersonResponseDefaultDto(Long id, String name, String cpf, LocalDate dateOfBirth) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
