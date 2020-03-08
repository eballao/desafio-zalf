package com.zalf.prolog.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<RelatorioMarcacao> listar(RelatorioFilter relatorioFilter){
		
		try {
			
			RelatorioMarcacao relatorioMarcacao = service.preparaRelatorioMarcacao(relatorioFilter);
			
			return ResponseEntity.ok(relatorioMarcacao);
			
		}catch (Exception e) {
			
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		
		}
		
	}

}
