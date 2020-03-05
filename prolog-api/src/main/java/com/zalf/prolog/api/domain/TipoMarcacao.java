package com.zalf.prolog.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="marcacao_tipo")
public class TipoMarcacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long codigo;
	
	private String nome;
	
	@Column(name = "tempo_recomendado_minutos")
	private Integer tempoRecomendadoMinutos;
	
}
