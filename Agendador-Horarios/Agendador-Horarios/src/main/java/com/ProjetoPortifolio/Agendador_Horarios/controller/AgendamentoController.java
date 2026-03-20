package com.ProjetoPortifolio.Agendador_Horarios.controller;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoPortifolio.Agendador_Horarios.infrastructure.entity.Agendamento;
import com.ProjetoPortifolio.Agendador_Horarios.infrastructure.services.AgendamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor

public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    // Método para criar um novo agendamento
    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento){
        return ResponseEntity.accepted().body(agendamentoService.salvarAgendamento(null));
    }

    // Método para deletar um agendamento existente por cliente e data/hora
    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente, @RequestParam LocalDateTime dataHoraAgendamento){
        agendamentoService.deletarAgendamento(dataHoraAgendamento, cliente);
        return ResponseEntity.noContent().build();
    }

    // Método para buscar agendamentos por dia
    @GetMapping
    public ResponseEntity<List<Agendamento>> buscarAgendamentoDia(@RequestParam LocalDate data){
        return ResponseEntity.ok().body(agendamentoService.buscarAgendamentosDia(data));
    }

    // Método para alterar um agendamento existente
    @PutMapping
    public ResponseEntity<Agendamento> alterarAgendamento(@RequestParam String cliente, @RequestParam LocalDateTime dataHoraAgendamento){
        return ResponseEntity.ok().body(agendamentoService.alterarAgendamento(null, cliente, dataHoraAgendamento));
    }

}
