package com.zalf.prolog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zalf.prolog.api.domain.MarcacaoVinculoInicioFim;
import com.zalf.prolog.api.repository.marcacao.MarcacaoVinculoInicioFimRepositoryQuery;

public interface MarcacaoVinculoInicioFimRepository extends JpaRepository<MarcacaoVinculoInicioFim, Long>, MarcacaoVinculoInicioFimRepositoryQuery {

}
