package com.zalf.prolog.api.resources;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zalf.prolog.api.domain.Marcacao;
import com.zalf.prolog.api.repository.MarcacaoRepository;
import com.zalf.prolog.api.repository.filter.RelatorioFilter;

@RestController
@RequestMapping(value="/marcacoes")
public class MarcacaoResource {
	
	@Autowired
	private MarcacaoRepository marcacaoRepository;
	
	@GetMapping("/teste")
	public List<Marcacao> listar(RelatorioFilter relatorioFilter){
		System.out.println("teste:  " + LocalDateTime.now());
		return marcacaoRepository.filtrar(relatorioFilter);
	}

}
