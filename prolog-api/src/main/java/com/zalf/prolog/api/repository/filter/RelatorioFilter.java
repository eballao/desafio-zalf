package com.zalf.prolog.api.repository.filter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioFilter {
	
	private String cpf;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTermino;
	
	public LocalDateTime getDataInicioConvertida() {
		LocalDateTime dataIni = getDataInicio().atStartOfDay().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
		
		ZonedDateTime teste = dataIni.atZone(ZoneId.of("UTC"));
		
		LocalDateTime dataUtc = dataIni.atZone(ZoneId.of("UTC")).toLocalDateTime();
		
		
		return dataUtc;
	}

}
