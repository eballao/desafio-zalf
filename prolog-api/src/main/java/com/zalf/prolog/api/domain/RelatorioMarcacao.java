package com.zalf.prolog.api.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RelatorioMarcacao {

//	private Colaborador colaborador;
	
	private List<MarcacaoVinculoInicioFim> marcacoes = new ArrayList<>();
	
	private List<TipoMarcacao> tipos = new ArrayList<>();

	public RelatorioMarcacao(List<MarcacaoVinculoInicioFim> marcacoes, List<TipoMarcacao> tipos) {
//		super();
		this.marcacoes = marcacoes;
		this.tipos = tipos;
	}
	
	public RelatorioMarcacao() {}
	
}
