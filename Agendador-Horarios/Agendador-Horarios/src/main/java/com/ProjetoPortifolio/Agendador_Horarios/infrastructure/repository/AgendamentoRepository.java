package com.ProjetoPortifolio.Agendador_Horarios.infrastructure.repository;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoPortifolio.Agendador_Horarios.infrastructure.entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Agendamento findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal);
}
