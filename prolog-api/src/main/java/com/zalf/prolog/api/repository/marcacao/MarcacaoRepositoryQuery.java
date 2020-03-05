package com.zalf.prolog.api.repository.marcacao;

import java.util.List;

import com.zalf.prolog.api.domain.Marcacao;
import com.zalf.prolog.api.repository.filter.RelatorioFilter;

public interface MarcacaoRepositoryQuery {

	public List<Marcacao> filtrar(RelatorioFilter relatorioFilter);
	
}
