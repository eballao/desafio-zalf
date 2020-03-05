package com.zalf.prolog.api.repository.marcacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zalf.prolog.api.domain.Marcacao;
import com.zalf.prolog.api.repository.filter.RelatorioFilter;

public class MarcacaoRepositoryImpl implements MarcacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Marcacao> filtrar(RelatorioFilter relatorioFilter) {
		final StringBuilder hqlBuilder = new StringBuilder();

		hqlBuilder.append("select ma from Marcacao ma ");
		hqlBuilder.append("where ma.colaborador.cpf = :cpf ");
		hqlBuilder.append("and ma.dataHoraMarcacao between :dataInicial and :dataTermino ");
//		hqlBuilder.append("and ma.dataHoraMarcacao <= :dataFim ");
//		hqlBuilder.append("and voo.saida <= :dataFinal ");
//		hqlBuilder.append("or voo.destino = :destino ");
//		hqlBuilder.append("and voo.saida >= :data ");
//		hqlBuilder.append("and voo.saida <= :dataFinal ");
//		hqlBuilder.append("order by voo.saida ");

		Query query = manager.createQuery(hqlBuilder.toString());
		query.setParameter("cpf", relatorioFilter.getCpf());
		query.setParameter("dataInicial", relatorioFilter.getDataInicio().atStartOfDay().plusHours(3));
		query.setParameter("dataTermino", relatorioFilter.getDataTermino().atTime(23, 59).plusHours(3));
//		query.setParameter("destino", vooFilter.getDestino());.atZone(ZoneId.of("UTC")).toLocalDateTime()
//		query.setParameter("data", vooFilter.getData().atStartOfDay())relatorioFilter.getDataInicioConvertida();

		return query.getResultList();
	}

}
