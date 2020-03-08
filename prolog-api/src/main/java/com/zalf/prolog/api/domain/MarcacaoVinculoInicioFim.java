package com.zalf.prolog.api.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="marcacao_vinculo_inicio_fim")
public class MarcacaoVinculoInicioFim implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long codigo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_marcacao_inicio")
	private Marcacao marcacaoInicio;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_marcacao_fim")
	private Marcacao marcacaoFim;
	
	@JsonIgnore
	public boolean isValid() {
		if(marcacaoInicio != null && marcacaoFim != null) {
			return true;
		}
		return false;
	}
	
}
