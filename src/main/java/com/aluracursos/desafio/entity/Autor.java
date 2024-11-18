package com.aluracursos.desafio.entity;

import com.aluracursos.desafio.model.DatosAutor;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private String fechaDeNac;
    private String fechDefunc;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    @Override
    public String toString() {
        return
                " nombre='" + nombre +
                        ", fechaDeNac='" + fechaDeNac +
                        ", fechDefunc='" + fechDefunc;
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNac = datosAutor.fechaDeNac();
        this.fechDefunc = datosAutor.fechDefunc();
    }

    public Autor() {
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNac() {
        return fechaDeNac;
    }

    public void setFechaDeNac(String fechaDeNac) {
        this.fechaDeNac = fechaDeNac;
    }

    public String getFechDefunc() {
        return fechDefunc;
    }

    public void setFechDefunc(String fechDefunc) {
        this.fechDefunc = fechDefunc;
    }

    public List<Libro> getLibros() {
        return libros;
    }

}
