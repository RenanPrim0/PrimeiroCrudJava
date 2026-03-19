package com.ProjetoPortifolio.Agendador_Horarios.infrastructure.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import com.ProjetoPortifolio.Agendador_Horarios.infrastructure.entity.Agendamento;
import com.ProjetoPortifolio.Agendador_Horarios.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    // Método para salvar um agendamento

    public Agendamento salvarAgendamento(Agendamento agendamento){

        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusMinutes(1);

        List<Agendamento> agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(),
                horaAgendamento, horaFim);


        if(!agendados.isEmpty()){
            throw new RuntimeException("Horário já está preenchido");
        }
        return agendamentoRepository.save(agendamento);
    }

        // Método para deletar agendamentos por cliente

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente){
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
    }

    // Método para buscar agendamentos por dia

    public List<Agendamento> buscarAgendamentosDia(LocalDate data){
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23, 59, 59);

       return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, horaFinalDia);
    }

    // Método para alterar um agendamento existente

    public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraAgendamento){
        Agendamento agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

        if(Objects.isNull(agenda)){
            throw new RuntimeException("Horário não está preenchido");
        }

        // Mantém o ID do agendamento original para garantir que seja atualizado em vez de criar um novo

        agendamento.setId(agenda.getId());
       return agendamentoRepository.save(agendamento);
    }
}