package com.zalf.prolog.api.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MarcacoesDia {

	private String data;
	
	private List<MarcacaoVinculoInicioFim> marcacoes = new ArrayList<>();
	
}
