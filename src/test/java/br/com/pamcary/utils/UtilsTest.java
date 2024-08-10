package br.com.pamcary.utils;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import org.junit.Test;
import br.com.pamcary.entities.dtos.request.PersonRequestDto;

public class UtilsTest {

	@Test
    public void testValidateAttributesDto_ValidDto() {
        PersonRequestDto validDto = new PersonRequestDto();
        validDto.setName("John Doe");
        validDto.setCpf("12345678900");
        validDto.setDateOfBirth(LocalDate.now());

        boolean result = Utils.validateAttributesDto(validDto);
        assertEquals(result, true);
    }

    @Test
    public void testValidateAttributesDto_InvalidDto_MissingName() {
        PersonRequestDto invalidDto = new PersonRequestDto();
        invalidDto.setName(null);
        invalidDto.setCpf("12345678900");
        invalidDto.setDateOfBirth(LocalDate.now());

        boolean result = Utils.validateAttributesDto(invalidDto);
        assertEquals(result, false);
    }

    @Test
    public void testValidateAttributesDto_InvalidDto_MissingCpf() {
        PersonRequestDto invalidDto = new PersonRequestDto();
        invalidDto.setName("John Doe");
        invalidDto.setCpf(null);
        invalidDto.setDateOfBirth(LocalDate.now());

        boolean result = Utils.validateAttributesDto(invalidDto);
        assertEquals(result, false);
    }

    @Test
    public void testValidateAttributesDto_InvalidDto_MissingDateOfBirth() {
        PersonRequestDto invalidDto = new PersonRequestDto();
        invalidDto.setName("John Doe");
        invalidDto.setCpf("12345678900");
        invalidDto.setDateOfBirth(null);

        boolean result = Utils.validateAttributesDto(invalidDto);
        assertEquals(result, false);
    }
}
