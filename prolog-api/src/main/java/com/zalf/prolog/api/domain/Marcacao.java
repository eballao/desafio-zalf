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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zalf.prolog.api.enums.DominioTipoMarcacao;
import com.zalf.prolog.api.utils.DataUtils;

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
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cpf_colaborador")
	private Colaborador colaborador;
	
	@JsonIgnore
	@Column(name = "data_hora_marcacao")
	private LocalDateTime dataHoraMarcacao;
	
	@Column(name = "tipo_marcacao", length = 15)
	@Enumerated(EnumType.STRING)
	private DominioTipoMarcacao domTipoMarcacao;
	
	public String getDataFormatada() {
		return DataUtils.getLocalDateFormatString(dataHoraMarcacao.minusHours(3).toLocalDate(), "dd-MM-yyyy");
	}
	
	public String getHoraFormatada() {
		return DataUtils.getLocalTimeFormatString(dataHoraMarcacao.minusHours(3).toLocalTime(), "HH:mm:ss");
	}
	
	@JsonIgnore
	public LocalDateTime getDataHoraMarcacaoAMSP() {
		return dataHoraMarcacao.minusHours(3);
	}
	
}
