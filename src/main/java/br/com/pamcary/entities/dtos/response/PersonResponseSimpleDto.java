package br.com.pamcary.entities.dtos.response;

public class PersonResponseSimpleDto {

	private String message;
	private Boolean isSuccess;
	
	public PersonResponseSimpleDto() {}
	
	public PersonResponseSimpleDto(String message, Boolean isSuccess) {
		super();
		this.message = message;
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
