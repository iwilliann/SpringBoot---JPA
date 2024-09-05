package com.exemplo.tarefa.service;

import com.exemplo.tarefa.model.Tarefa;
import com.exemplo.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa inserir(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tarefa não encontrada"));
    }

    public List<Tarefa> listarFinalizadas(boolean finalizado) {
        return tarefaRepository.findByFinalizado(finalizado);
    }

    public List<Tarefa> listarAtrasadas() {
        return tarefaRepository.findByFinalizadoFalseAndDataPrevistaFinalizacaoBefore(LocalDate.now());
    }

    public List<Tarefa> listarNaoFinalizadasEntreDatas(LocalDate inicio, LocalDate fim) {
        return tarefaRepository.findByFinalizadoAndDataPrevistaFinalizacaoBetween(false, inicio, fim);
    }

    public Tarefa atualizar(Long id, Tarefa tarefaAtualizada) {
        Tarefa tarefaExistente = buscarPorId(id);
        if (tarefaExistente.isFinalizado()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível modificar uma tarefa finalizada.");
        }

        tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
        tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
        tarefaExistente.setDataPrevistaFinalizacao(tarefaAtualizada.getDataPrevistaFinalizacao());
        return tarefaRepository.save(tarefaExistente);
    }

    public void deletar(Long id) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa.isFinalizado()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível deletar uma tarefa finalizada.");
        }
        tarefaRepository.deleteById(id);
    }

    public Tarefa finalizar(Long id) {
        Tarefa tarefa = buscarPorId(id);
        if (!tarefa.isFinalizado()) {
            tarefa.setFinalizado(true);
            tarefa.setDataFinalizacao(LocalDate.now());
            return tarefaRepository.save(tarefa);
        }
        throw new ResponseStatusException(BAD_REQUEST, "A tarefa já está finalizada.");
    }
}
