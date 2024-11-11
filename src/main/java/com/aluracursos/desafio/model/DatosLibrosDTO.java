package com.aluracursos.desafio.model;

import java.util.List;

public class DatosLibrosDTO {
    private String titulo;
    private List<DatosAutor> autor;
    private List<String> idiomas;
    private Double numeroDeDescargas;

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                        ", autor=" + autor +
                        ", idiomas=" + idiomas +
                        ", numeroDeDescargas=" + numeroDeDescargas;

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

    public DatosLibrosDTO(String titulo, List<DatosAutor> autor, List<String> idiomas, Double numeroDeDescargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
