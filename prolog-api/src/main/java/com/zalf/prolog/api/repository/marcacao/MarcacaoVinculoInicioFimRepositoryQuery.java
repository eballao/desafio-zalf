package com.zalf.prolog.api.repository.marcacao;

import java.util.List;

import com.zalf.prolog.api.domain.MarcacaoVinculoInicioFim;
import com.zalf.prolog.api.repository.filter.RelatorioFilter;

public interface MarcacaoVinculoInicioFimRepositoryQuery {
	
	public List<MarcacaoVinculoInicioFim> filtrar(RelatorioFilter relatorioFilter);

}
