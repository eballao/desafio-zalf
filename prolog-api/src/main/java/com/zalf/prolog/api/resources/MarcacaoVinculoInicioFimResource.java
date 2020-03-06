package com.zalf.prolog.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zalf.prolog.api.domain.MarcacaoVinculoInicioFim;
import com.zalf.prolog.api.domain.TipoMarcacaoHoras;
import com.zalf.prolog.api.repository.MarcacaoVinculoInicioFimRepository;
import com.zalf.prolog.api.repository.filter.RelatorioFilter;
import com.zalf.prolog.api.service.MarcacaoService;

@RestController
@RequestMapping(value="/marcacoes-vinculos")
public class MarcacaoVinculoInicioFimResource {
	
	@Autowired
	private MarcacaoVinculoInicioFimRepository marcacaoVinculoInicioFimRepository;
	
	@Autowired
	private MarcacaoService service;
	
	@GetMapping("/teste")
	public List<MarcacaoVinculoInicioFim> listar(RelatorioFilter relatorioFilter){
		System.out.println("teste");
		
		List<MarcacaoVinculoInicioFim> marcs = marcacaoVinculoInicioFimRepository.filtrar(relatorioFilter);
		
		List<TipoMarcacaoHoras> tipos = service.getTipoMarcacaoHoras(marcs);
		
		return marcacaoVinculoInicioFimRepository.filtrar(relatorioFilter);
	}

}
