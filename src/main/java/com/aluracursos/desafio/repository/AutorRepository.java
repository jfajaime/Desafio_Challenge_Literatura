package com.aluracursos.desafio.repository;

import com.aluracursos.desafio.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);
    @Query("SELECT s FROM Autor s WHERE s.fechaDeNac <= :año AND s.fechDefunc >= :año")
    List<Autor> listarAutoresVivosPorFecha(int año);
}
