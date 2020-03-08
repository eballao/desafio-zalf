package com.zalf.prolog.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zalf.prolog.api.domain.Colaborador;
import com.zalf.prolog.api.repository.ColaboradorRepository;

@RestController
@RequestMapping(value="/colaboradores")
public class ColaboradorResource {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@GetMapping("/teste")
	public List<Colaborador> listar(){
		return colaboradorRepository.findAll();
	}

}
