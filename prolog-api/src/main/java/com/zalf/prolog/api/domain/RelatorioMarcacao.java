package com.zalf.prolog.api.domain;

import java.util.ArrayList;
import java.util.List;

import com.zalf.prolog.api.repository.filter.RelatorioFilter;

import lombok.Data;

@Data
public class RelatorioMarcacao {

	private RelatorioFilter relatorioFilter;
	
	private Colaborador colaborador;
	
	private List<MarcacoesDia> marcacoes = new ArrayList<>();
	
	private List<TipoMarcacaoHoras> tipos = new ArrayList<>();

	public RelatorioMarcacao(List<MarcacoesDia> marcacoes, List<TipoMarcacaoHoras> tipos) {
//		super();
		this.marcacoes = marcacoes;
		this.tipos = tipos;
	}
	
	public RelatorioMarcacao() {}
	
}
