package com.aluracursos.desafio.entity;

import com.aluracursos.desafio.model.DatosLibros;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne()
    private Autor autor;
    private String idiomas;
    private Double numeroDeDescargas;

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
//        this.autor = datosLibros.autor();
        this.idiomas = datosLibros.idiomas().get(0);
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }

    public Libro() {
    }

    public Libro(String dato) {
    }

    //    public Libros(com.aluracursos.desafio.model.DatosLibros datosLibros) {
//        this.titulo= datosLibros.titulo();
////        this.autor = datosLibros.autor().get(0);
//        this.idiomas = datosLibros.idiomas().get(0);
//        this.numeroDeDescargas= datosLibros.numeroDeDescargas();
//    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                        ", autor=" + autor.getNombre() +
                        ", idiomas=" + idiomas +
                        ", numeroDeDescargas=" + numeroDeDescargas;
    }
}
