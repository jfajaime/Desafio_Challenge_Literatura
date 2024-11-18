package com.aluracursos.desafio.repository;

import com.aluracursos.desafio.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibrosRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdiomas(String idiomas);
}
