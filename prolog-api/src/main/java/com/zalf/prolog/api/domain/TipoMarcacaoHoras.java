package com.zalf.prolog.api.domain;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zalf.prolog.api.utils.DataUtils;

import lombok.Data;

@Data
public class TipoMarcacaoHoras {

	private String nome;
	
	@JsonIgnore
	private Integer tempoRecomendadoMinutos;
	
	@JsonIgnore
	private Duration totalPeriodo = Duration.ZERO;
	
	@JsonIgnore
	private Duration horasNoturnasClt = Duration.ZERO;
	
	public String getTempoRecomendadoFormatado() {
		return DataUtils.getIntegerMinutosFormatString(tempoRecomendadoMinutos);
	}
	
	public String getTotalPeriodoFormatado() {
		return DataUtils.getDurationFormatString(totalPeriodo);
	}
	
	public String getHorasNoturnasCltFormatado() {
		return DataUtils.getDurationFormatString(horasNoturnasClt);
	}
	
}
