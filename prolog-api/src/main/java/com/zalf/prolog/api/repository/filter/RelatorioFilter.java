package com.zalf.prolog.api.repository.filter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zalf.prolog.api.utils.DataUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioFilter {
	
	private String cpf;
	
	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;
	
	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTermino;
	
	public String getDataInicioFormatada() {
		return DataUtils.getLocalDateFormatString(dataInicio, "dd-MM-yyyy");
	}
	
	public String getDataTerminoFormatada() {
		return DataUtils.getLocalDateFormatString(dataTermino, "dd-MM-yyyy");
	}
	
	public String getDataEmissaoRelatorio() {
		return DataUtils.getLocalDateTimeFormatString(LocalDateTime.now(), "dd-MM-yyyy HH:mm:ss");
	}
	
}
