package com.horton.test1.controller;

import com.horton.test1.entity.Curso;
import com.horton.test1.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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
    public ResponseEntity<Curso> postCurso(@RequestBody Curso curso) {
        Curso saved = cursoRepository.save(curso);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<Curso> putCurso(@PathVariable Long id, @RequestBody final Curso curso) {
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


}