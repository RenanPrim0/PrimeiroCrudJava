package com.ProjetoPortifolio.Agendador_Horarios.infrastructure.services;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ProjetoPortifolio.Agendador_Horarios.infrastructure.entity.Agendamento;
import com.ProjetoPortifolio.Agendador_Horarios.infrastructure.repository.AgendamentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) {
        
        LocalDateTime dataHoraAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);

        Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(), dataHoraAgendamento, horaFim);

        if(Objects.nonNull(agendados)){
            throw new RuntimeException("Já existe um agendamento para esse serviço nesse horário.");
        }
        return agendamentoRepository.save(agendamento);

    }

}
