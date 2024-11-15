package com.aluracursos.desafio.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="libros")
public class DatosLibrosClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
//    @OneToMany
//    @JoinColumn(name = "author_id")
    @Transient
    private List<DatosAutor> autor;
    private List<String> idiomas;
    private Double numeroDeDescargas;
    public DatosLibrosClass(String titulo, List<DatosAutor> autor, List<String> idiomas, Double numeroDeDescargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public DatosLibrosClass(DatosLibros datosLibros) {
        this.titulo= datosLibros.titulo();
        this.autor = datosLibros.autor();
        this.idiomas = datosLibros.idiomas();
        this.numeroDeDescargas= datosLibros.numeroDeDescargas();
    }

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

    public List<DatosAutor> getAutor() {
        return autor;
    }

    public void setAutor(List<DatosAutor> autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
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
                ", autor=" + autor +
                ", idiomas=" + idiomas +
                ", numeroDeDescargas=" + numeroDeDescargas +
                '}';
    }
}
