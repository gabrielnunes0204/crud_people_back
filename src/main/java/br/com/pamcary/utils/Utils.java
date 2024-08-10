package br.com.pamcary.utils;

import br.com.pamcary.entities.dtos.request.PersonRequestDto;

public class Utils {
	
	public static boolean validateAttributesDto(PersonRequestDto requestDto) {
		
		if (requestDto.getName() == null || "".equals(requestDto.getName())) {
			return false;
		}
		
		if (requestDto.getCpf() == null || "".equals(requestDto.getCpf())) {
			return false;
		}
		
		if (requestDto.getDateOfBirth() == null) {
			return false;
		}
		
		return true;
	}
}
