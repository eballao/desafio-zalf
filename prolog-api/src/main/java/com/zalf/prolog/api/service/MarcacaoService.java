package com.zalf.prolog.api.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zalf.prolog.api.domain.MarcacaoVinculoInicioFim;
import com.zalf.prolog.api.domain.RelatorioMarcacao;
import com.zalf.prolog.api.domain.TipoMarcacaoHoras;
import com.zalf.prolog.api.repository.MarcacaoVinculoInicioFimRepository;
import com.zalf.prolog.api.repository.filter.RelatorioFilter;

@Service
public class MarcacaoService {
	
	@Autowired
	private MarcacaoVinculoInicioFimRepository marcacaoVinculoInicioFimRepository;
	
	public List<RelatorioMarcacao> preparaRelatorioMarcacao(final RelatorioFilter relatorioFilter){
		final List<MarcacaoVinculoInicioFim> marcacoes = marcacaoVinculoInicioFimRepository.filtrar(relatorioFilter);
		List<TipoMarcacaoHoras> tiposMarcacoesHoras = getTipoMarcacaoHoras(marcacoes);
		return null;
	}
	
	public List<TipoMarcacaoHoras> getTipoMarcacaoHoras(List<MarcacaoVinculoInicioFim> marcacoes){
		Map<String, TipoMarcacaoHoras> map = new HashMap<>();
//		List<TipoMarcacaoHoras> horas = new ArrayList<>();
		for(MarcacaoVinculoInicioFim marcacaoVinculoInicioFim : marcacoes) {
			TipoMarcacaoHoras hora = map.get(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getNome());
			if(hora == null) {
				hora = new TipoMarcacaoHoras();
				hora.setNome(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getNome());
				hora.setTempoRecomendadoMinutos(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getTempoRecomendadoMinutos());
				hora.setTotalPeriodo(calculaTotalPeriodo(hora.getTotalPeriodo(), marcacaoVinculoInicioFim));
				hora.setHorasNoturnasClt(calculaHorasNoturnas(hora.getHorasNoturnasClt(), marcacaoVinculoInicioFim));
				map.put(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getNome(), hora);
			} else {
				TipoMarcacaoHoras nextHora = new TipoMarcacaoHoras();
				nextHora.setNome(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getNome());
				nextHora.setTempoRecomendadoMinutos(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getTempoRecomendadoMinutos());
				nextHora.setTotalPeriodo(calculaTotalPeriodo(hora.getTotalPeriodo(), marcacaoVinculoInicioFim));
				nextHora.setHorasNoturnasClt(calculaHorasNoturnas(hora.getHorasNoturnasClt(), marcacaoVinculoInicioFim));
				map.put(marcacaoVinculoInicioFim.getMarcacaoInicio().getTipoMarcacao().getNome(), nextHora);
			}
		}
//		List<String> result2 = new ArrayList(map.values());
		return (List<TipoMarcacaoHoras>) new ArrayList(map.values());
	}

	private Duration calculaHorasNoturnas(Duration horasNoturnasClt, MarcacaoVinculoInicioFim marcacaoVinculoInicioFim) {
		LocalDateTime marcacaoHoraInicio = marcacaoVinculoInicioFim.getMarcacaoInicio().getDataHoraMarcacao();
		LocalDateTime marcacaoHoraFim = marcacaoVinculoInicioFim.getMarcacaoFim().getDataHoraMarcacao();
		if(marcacaoHoraInicio.getDayOfMonth() != marcacaoHoraFim.getDayOfMonth() || marcacaoHoraInicio.getHour() > 19 || marcacaoHoraFim.getHour() < 2) {
			LocalDate ld = marcacaoHoraInicio.toLocalDate();
			LocalDate ldF = marcacaoHoraFim.toLocalDate();
			
			LocalTime lTinicio = LocalTime.of(19, 00);
			LocalTime lTfim = LocalTime.of(02, 00);
			
			LocalDateTime ldtInicio = ld.atTime(19, 00);
			LocalDateTime ldtFim = marcacaoHoraFim.toLocalTime().isBefore(ldF.atTime(02, 00).toLocalTime()) ? ldF.atTime(02, 00) : ldF.atTime(02, 00).plusDays(1);
			
			LocalDateTime afterDate;
			LocalDateTime beforeDate;
			
			if(ldtFim.toLocalTime().isAfter(marcacaoHoraFim.toLocalTime())) {
				afterDate = marcacaoHoraFim;
			}else {
				afterDate = ldtFim;
			}
			
			if(ldtInicio.toLocalTime().isAfter(marcacaoHoraInicio.toLocalTime())) {
				beforeDate = ldtInicio;
			}else {
				beforeDate = marcacaoHoraInicio;
			}
			Duration duracaoCalc = Duration.between(beforeDate, afterDate);
			
			if(horasNoturnasClt != null) {
				duracaoCalc.plus(duracaoCalc);
			}
			
			return duracaoCalc;
			
		}
		
		return Duration.ZERO;
	}

	private Duration calculaTotalPeriodo(Duration totalPeriodo, MarcacaoVinculoInicioFim marcacaoVinculoInicioFim) {
		Duration duracao = Duration.between(marcacaoVinculoInicioFim.getMarcacaoInicio().getDataHoraMarcacao(), 
				marcacaoVinculoInicioFim.getMarcacaoFim().getDataHoraMarcacao());
		if(totalPeriodo != null) {
			return duracao.plus(totalPeriodo);
		}
		return duracao;
	}
	
	

}
