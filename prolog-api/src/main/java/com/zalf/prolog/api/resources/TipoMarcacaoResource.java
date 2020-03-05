package com.zalf.prolog.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zalf.prolog.api.domain.TipoMarcacao;
import com.zalf.prolog.api.repository.TipoMarcacaoRepository;

@RestController
@RequestMapping(value="/tipos-marcacao")
public class TipoMarcacaoResource {
	
	@Autowired
	private TipoMarcacaoRepository tipoMarcacaoRepository;
	
	@GetMapping("/teste")
	public List<TipoMarcacao> listar(){
		System.out.println("teste");
		return tipoMarcacaoRepository.findAll();
	}

}
