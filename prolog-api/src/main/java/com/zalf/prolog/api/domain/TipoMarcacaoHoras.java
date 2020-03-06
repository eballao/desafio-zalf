package com.zalf.prolog.api.domain;

import java.time.Duration;

import lombok.Data;

@Data
public class TipoMarcacaoHoras {

	private String nome;
	
	private Integer tempoRecomendadoMinutos;
	
	private Duration totalPeriodo;
	
	private Duration horasNoturnasClt;
	
}
