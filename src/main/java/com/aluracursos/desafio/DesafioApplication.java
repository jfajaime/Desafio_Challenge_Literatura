package com.aluracursos.desafio;

import com.aluracursos.desafio.principal.PrincipalConMenu;
import com.aluracursos.desafio.repository.AutorRepository;
import com.aluracursos.desafio.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {
    @Autowired
    private LibrosRepository repositorio;
    @Autowired
    private AutorRepository repositorioA;

    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//		Principal principal = new Principal();
//		principal.muestraMenu();
        PrincipalConMenu principalConMenu = new PrincipalConMenu(repositorio, repositorioA);
        principalConMenu.muestraMenu();
    }
}
