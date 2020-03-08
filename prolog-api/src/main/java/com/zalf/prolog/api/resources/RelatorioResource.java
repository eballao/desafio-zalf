package com.zalf.prolog.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zalf.prolog.api.domain.RelatorioMarcacao;
import com.zalf.prolog.api.repository.filter.RelatorioFilter;
import com.zalf.prolog.api.service.MarcacaoService;

@RestController
@RequestMapping(value="/relatorios")
public class RelatorioResource {
	
	@Autowired
	private MarcacaoService service;
	
	@GetMapping("/marcacoes")
	public RelatorioMarcacao listar(RelatorioFilter relatorioFilter){
		
		RelatorioMarcacao relatorioMarcacao = service.preparaRelatorioMarcacao(relatorioFilter);
		
		return relatorioMarcacao;
	}

}
