package com.exemplo.tarefa.controller;

import com.exemplo.tarefa.model.Tarefa;
import com.exemplo.tarefa.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa inserir(@Valid @RequestBody Tarefa tarefa) {
        return tarefaService.inserir(tarefa);
    }

    @GetMapping
    public List<Tarefa> listarTodas() {
        return tarefaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Tarefa buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
        return tarefaService.atualizar(id, tarefa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
    }

    @GetMapping("/finalizadas")
    public List<Tarefa> listarFinalizadas() {
        return tarefaService.listarFinalizadas(true);
    }

    @GetMapping("/nao-finalizadas")
    public List<Tarefa> listarNaoFinalizadas() {
        return tarefaService.listarFinalizadas(false);
    }

    @GetMapping("/atrasadas")
    public List<Tarefa> listarAtrasadas() {
        return tarefaService.listarAtrasadas();
    }

    @GetMapping("/nao-finalizadas-entre")
    public List<Tarefa> listarNaoFinalizadasEntreDatas(
        @RequestParam("inicio") LocalDate inicio,
        @RequestParam("fim") LocalDate fim) {
        return tarefaService.listarNaoFinalizadasEntreDatas(inicio, fim);
    }

    @PatchMapping("/{id}/finalizar")
    public Tarefa finalizar(@PathVariable Long id) {
        return tarefaService.finalizar(id);
    }
}
