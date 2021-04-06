package com.horton.test1.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    @Column(unique = true)
    @NotBlank(message = "Email es requerido")
    private String email;
    private String observaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="curso_id")
    private Curso curso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Curso getCurso() {
        curso.setParticipantes(null);
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", curso=" + curso +
                '}';
    }
}
