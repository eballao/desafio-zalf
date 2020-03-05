package com.zalf.prolog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zalf.prolog.api.domain.Marcacao;
import com.zalf.prolog.api.repository.marcacao.MarcacaoRepositoryQuery;

public interface MarcacaoRepository extends JpaRepository<Marcacao, Long>, MarcacaoRepositoryQuery {

}
