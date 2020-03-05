package com.zalf.prolog.api.repository.marcacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zalf.prolog.api.domain.MarcacaoVinculoInicioFim;
import com.zalf.prolog.api.repository.filter.RelatorioFilter;

public class MarcacaoVinculoInicioFimRepositoryImpl implements MarcacaoVinculoInicioFimRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<MarcacaoVinculoInicioFim> filtrar(RelatorioFilter relatorioFilter) {
		final StringBuilder hqlBuilder = new StringBuilder();

		hqlBuilder.append("select mv from MarcacaoVinculoInicioFim mv ");
		hqlBuilder.append("where (mv.marcacaoInicio.colaborador.cpf = :cpf ");
		hqlBuilder.append("or mv.marcacaoFim.colaborador.cpf = :cpf) ");
		hqlBuilder.append("and (mv.marcacaoInicio.dataHoraMarcacao between :dataInicial and :dataTermino ");
		hqlBuilder.append("or mv.marcacaoFim.dataHoraMarcacao between :dataInicial and :dataTermino) ");
		hqlBuilder.append("order by mv.marcacaoInicio.dataHoraMarcacao ");
//		hqlBuilder.append("and voo.saida <= :dataFinal ");
//		hqlBuilder.append("or voo.destino = :destino ");
//		hqlBuilder.append("and voo.saida >= :data ");
//		hqlBuilder.append("and voo.saida <= :dataFinal ");

		Query query = manager.createQuery(hqlBuilder.toString());
		query.setParameter("cpf", relatorioFilter.getCpf());
		query.setParameter("dataInicial", relatorioFilter.getDataInicio().atStartOfDay().plusHours(3));
		query.setParameter("dataTermino", relatorioFilter.getDataTermino().atTime(23, 59).plusHours(3));
//		query.setParameter("destino", vooFilter.getDestino());.atZone(ZoneId.of("UTC")).toLocalDateTime()
//		query.setParameter("data", vooFilter.getData().atStartOfDay())relatorioFilter.getDataInicioConvertida();

		return query.getResultList();
	}

}
