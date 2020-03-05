package com.zalf.prolog.api.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zalf.prolog.api.enums.DominioTipoMarcacao;

import lombok.Data;

@Data
@Entity
@Table(name="marcacao")
public class Marcacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long codigo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_tipo_marcacao")
	private TipoMarcacao tipoMarcacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cpf_colaborador")
	private Colaborador colaborador;
	
	@Column(name = "data_hora_marcacao")
	private LocalDateTime dataHoraMarcacao;
	
	@Column(name = "tipo_marcacao", length = 15)
	@Enumerated(EnumType.STRING)
	private DominioTipoMarcacao domTipoMarcacao;
	
}
