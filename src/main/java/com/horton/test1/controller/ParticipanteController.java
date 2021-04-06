package com.horton.test1.controller;

import com.horton.test1.entity.Participante;
import com.horton.test1.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Validated
public class ParticipanteController {

    private ParticipanteRepository participanteRepository;

    @Autowired
    public ParticipanteController(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }


    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<List> handleConstraintViolationException(BindException e) {
        return new ResponseEntity<>(e.getAllErrors()
                .stream().map(objectError -> {
                    Map<String, String> error = new HashMap<>();
                    error.put("message", objectError.getDefaultMessage());
                    return error;
                })
                .collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/participantes")
    public List<Participante> getAllParticipantes() {
        return participanteRepository.findAll();
    }

    @GetMapping("/participantes/{id}")
    public ResponseEntity<Participante> getByIdParticipante(@PathVariable Long id) {
        Optional<Participante> paticipanteOptional = participanteRepository.findById(id);
        if (!paticipanteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paticipanteOptional.get());
    }

    @GetMapping("/participantes/by-name/{nombre}")
    public ResponseEntity<Participante> getByNameParticipante(@PathVariable String nombre) {
        Optional<Participante> paticipanteOptional = participanteRepository.findByNombre(nombre);
        if (!paticipanteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paticipanteOptional.get());
    }

    @PostMapping("/participantes")
    public ResponseEntity<Participante> postParticipante(@Valid @RequestBody Participante participante) {
        Participante saved = participanteRepository.save(participante);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @PutMapping("/participantes/{id}")
    public ResponseEntity<Participante> putParticipante(@PathVariable Long id, @Valid @RequestBody Participante participante) {
        Optional<Participante> participanteOptional = participanteRepository.findById(id);
        if (!participanteOptional.isPresent())
            return ResponseEntity.notFound().build();
        participante.setId(id);
        return new ResponseEntity(participanteRepository.save(participante), HttpStatus.OK);
    }

    @DeleteMapping("/participantes/{id}")
    public void deleteParticipante(@PathVariable Long id) {
        participanteRepository.deleteById(id);
    }


}