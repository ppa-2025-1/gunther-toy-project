package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NewChamado;
import com.example.demo.dto.NewUser;
import com.example.demo.model.business.ChamadoBusiness;
import com.example.demo.model.entity.Chamado;
import com.example.demo.model.entity.User;
import com.example.demo.repository.ChamadoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/chamados")
public class ChamadoController extends AbstractController {
    private final ChamadoBusiness chamadoBusiness;
    private final ChamadoRepository chamadoRepository;

    public ChamadoController(ChamadoBusiness chamadoBusiness, ChamadoRepository chamadoRepository) {
        this.chamadoBusiness = chamadoBusiness;
        this.chamadoRepository = chamadoRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewChamado(
        @Valid
        @RequestBody
        NewChamado newChamado) {

        chamadoBusiness.criarChamado(newChamado);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Chamado>> getUsers() {
        return ResponseEntity.ok(chamadoRepository.findAll());
    }
}
