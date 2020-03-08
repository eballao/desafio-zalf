package com.zalf.prolog.api.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zalf.prolog.api.domain.Colaborador;
import com.zalf.prolog.api.domain.MarcacaoVinculoInicioFim;
import com.zalf.prolog.api.domain.MarcacoesDia;
import com.zalf.prolog.api.domain.RelatorioMarcacao;
import com.zalf.prolog.api.domain.TipoMarcacaoHoras;
import com.zalf.prolog.api.repository.MarcacaoVinculoInicioFimRepository;
import com.zalf.prolog.api.repository.filter.RelatorioFilter;

@Service
public class MarcacaoService {
	
	@Autowired
	private MarcacaoVinculoInicioFimRepository marcacaoVinculoInicioFimRepository;
	
	public static final Integer HORA_INICIO = 22;
	public static final Integer HORA_FINAL = 5;
	public static final Integer MINUTO = 0;
	
	public RelatorioMarcacao preparaRelatorioMarcacao(RelatorioFilter relatorioFilter){
		List<MarcacaoVinculoInicioFim> marcacoes = marcacaoVinculoInicioFimRepository.filtrar(relatorioFilter);
		
		Map<String, TipoMarcacaoHoras> map = new HashMap<>();
		Map<LocalDate, MarcacoesDia> map2 = new HashMap<>();
		for(MarcacaoVinculoInicioFim marcacaoVinculoInicioFim : marcacoes) {
			map = populaMapaTiposMarcacoes(map, marcacaoVinculoInicioFim);
			map2 = populaMarcacosPorData(map2, marcacaoVinculoInicioFim);
		}
		RelatorioMarcacao relatorioMarcacao = new RelatorioMarcacao();
		relatorioMarcacao.setRelatorioFilter(relatorioFilter);
		Map<LocalDate, MarcacoesDia> treeMap = new TreeMap<LocalDate, MarcacoesDia>(map2);
		relatorioMarcacao.setTipos((List<TipoMarcacaoHoras>) new ArrayList(map.values()));
		relatorioMarcacao.setMarcacoes((List<MarcacoesDia>) new ArrayList(treeMap.values()));
		
		if(marcacoes != null && !marcacoes.isEmpty()) {
			Colaborador colaborador = marcacoes.get(0).getMarcacaoInicio().getColaborador();
			if(colaborador != null) {				
				relatorioMarcacao.setColaborador(colaborador);
			}else {
				relatorioMarcacao.setColaborador(marcacoes.get(0).getMarcacaoFim().getColaborador());
			}
		}
		
		return relatorioMarcacao;
	}

	private Map<LocalDate, MarcacoesDia> populaMarcacosPorData(
			Map<LocalDate, MarcacoesDia> map2, MarcacaoVinculoInicioFim marcacaoVinculoInicioFim) {
		MarcacoesDia marcacoes = map2.get(marcacaoVinculoInicioFim.getMarcacaoInicio().getDataHoraMarcacaoAMSP().toLocalDate());
		if(marcacoes == null) {
			marcacoes = new MarcacoesDia();
			marcacoes.setData(marcacaoVinculoInicioFim.getMarcacaoInicio().getDataHoraMarcacaoAMSP().toLocalDate().toString());
			marcacoes.getMarcacoes().add(marcacaoVinculoInicioFim);
			map2.put(marcacaoVinculoInicioFim.getMarcacaoInicio().getDataHoraMarcacaoAMSP().toLocalDate(), marcacoes);
		} else {
			marcacoes.getMarcacoes().add(marcacaoVinculoInicioFim);
			map2.put(marcacaoVinculoInicioFim.getMarcacaoInicio().getDataHoraMarcacaoAMSP().toLocalDate(), marcacoes);
		}
		return map2;
	}

	private Map<String, TipoMarcacaoHoras> populaMapaTiposMarcacoes(Map<String, TipoMarcacaoHoras> map, MarcacaoVinculoInicioFim marcacaoVinculoInicioFim) {
		TipoMarcacaoHoras hora = map.get(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getNome());
		if(hora == null) {
			hora = new TipoMarcacaoHoras();
			hora.setNome(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getNome());
			hora.setTempoRecomendadoMinutos(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getTempoRecomendadoMinutos());
			if(marcacaoVinculoInicioFim.isValid()) {				
				hora.setTotalPeriodo(calculaTotalPeriodo(hora.getTotalPeriodo(), marcacaoVinculoInicioFim));
				hora.setHorasNoturnasClt(calculaHorasNoturnas(hora.getHorasNoturnasClt(), marcacaoVinculoInicioFim));
			}
			map.put(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getNome(), hora);
		} else {
			TipoMarcacaoHoras nextHora = new TipoMarcacaoHoras();
			nextHora.setNome(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getNome());
			nextHora.setTempoRecomendadoMinutos(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getTempoRecomendadoMinutos());
			nextHora.setTotalPeriodo(calculaTotalPeriodo(hora.getTotalPeriodo(), marcacaoVinculoInicioFim));
			nextHora.setHorasNoturnasClt(calculaHorasNoturnas(hora.getHorasNoturnasClt(), marcacaoVinculoInicioFim));
			map.put(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getNome(), nextHora);
		}
		return map;
	}

	private Duration calculaHorasNoturnas(Duration horasNoturnasClt, MarcacaoVinculoInicioFim marcacaoVinculoInicioFim) {
		if(marcacaoVinculoInicioFim.isValid()) {
			
			LocalDateTime marcacaoHoraInicio = marcacaoVinculoInicioFim.getMarcacaoInicio().getDataHoraMarcacaoAMSP();
			LocalDateTime marcacaoHoraFim = marcacaoVinculoInicioFim.getMarcacaoFim().getDataHoraMarcacaoAMSP();
			LocalDate ld = marcacaoHoraInicio.toLocalDate();
			LocalDate ldF = marcacaoHoraFim.toLocalDate();
			
			LocalDateTime ldtInicio = marcacaoHoraInicio.isBefore(ld.atTime(HORA_FINAL, MINUTO)) ? ld.atTime(HORA_INICIO, MINUTO).minusDays(1) : ld.atTime(HORA_INICIO, MINUTO);
			LocalDateTime ldtFim = marcacaoHoraFim.isAfter(ldF.atTime(HORA_INICIO, MINUTO)) ? ldF.atTime(HORA_FINAL, MINUTO).plusDays(1) : ldF.atTime(HORA_FINAL, MINUTO);
			if(marcacaoHoraInicio.getDayOfMonth() != marcacaoHoraFim.getDayOfMonth() || marcacaoHoraInicio.isAfter(ldtInicio) || marcacaoHoraFim.isBefore(ldtFim)) {
				
				LocalDateTime afterDate;
				LocalDateTime beforeDate;
				
				if(ldtFim.isAfter(marcacaoHoraFim)) {
					afterDate = marcacaoHoraFim;
				}else {
					afterDate = ldtFim;
				}
				
				if(ldtInicio.isAfter(marcacaoHoraInicio)) {
					beforeDate = ldtInicio;
				}else {
					beforeDate = marcacaoHoraInicio;
				}
				
				Duration duracaoCalc = Duration.between(beforeDate, afterDate);
				
				return duracaoCalc.plus(horasNoturnasClt);
				
			}
		}
		
		return horasNoturnasClt;
	}

	private Duration calculaTotalPeriodo(Duration totalPeriodo, MarcacaoVinculoInicioFim marcacaoVinculoInicioFim) {
		if(marcacaoVinculoInicioFim.isValid()) {			
			Duration duracao = Duration.between(marcacaoVinculoInicioFim.getMarcacaoInicio().getDataHoraMarcacao(), 
					marcacaoVinculoInicioFim.getMarcacaoFim().getDataHoraMarcacao());
			return duracao.plus(totalPeriodo);
		}
		return totalPeriodo;
	}
	
}
