package com.horton.test1.controller;

import com.horton.test1.entity.Curso;
import com.horton.test1.repository.CursoRepository;
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
public class CursoController {

    private CursoRepository cursoRepository;

    @Autowired
    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }


    @GetMapping("/cursos")
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<Curso> getByIdCurso(@PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);
        if (!cursoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursoOptional.get());
    }

    @PostMapping("/cursos")
    public ResponseEntity<Curso> postCurso(@Valid @RequestBody Curso curso) {
        Curso saved = cursoRepository.save(curso);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<Curso> putCurso(@PathVariable Long id, @Valid @RequestBody Curso curso) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);
        if (!cursoOptional.isPresent())
            return ResponseEntity.notFound().build();
        curso.setId(id);
        return new ResponseEntity(cursoRepository.save(curso), HttpStatus.OK);
    }

    @DeleteMapping("/cursos/{id}")
    public void deleteCurso(@PathVariable Long id) {
        cursoRepository.deleteById(id);
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

}