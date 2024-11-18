package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.entity.Autor;
import com.aluracursos.desafio.entity.Libro;
import com.aluracursos.desafio.model.Datos;
import com.aluracursos.desafio.model.DatosLibros;
import com.aluracursos.desafio.repository.AutorRepository;
import com.aluracursos.desafio.repository.LibrosRepository;
import com.aluracursos.desafio.service.ConsumoAPI;
import com.aluracursos.desafio.service.ConvierteDatos;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import java.util.*;

public class PrincipalConMenu {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private static final String URL_BASE = "https://gutendex.com/books/";
    Scanner teclado = new Scanner(System.in);
    private AutorRepository repositoryA;
    private LibrosRepository repository;

    public PrincipalConMenu(LibrosRepository repositorio, AutorRepository repositorioA) {
        this.repository = repositorio;
        this.repositoryA = repositorioA;
    }

    public void muestraMenu() {
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            String menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libro registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libro por idioma
                    0 - Salir
                    - Ingrese la opcion deseada a continuacion: !
                    """;
            System.out.println(menu);

            // Validar que la entrada sea un número
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listaLibros();
                        break;
                    case 3:
//                        getDatosLibros();
                        break;
                    case 0:
                        System.out.println("Gracias por utilizar la aplicacion.!");
                        break;
                    default:
                        System.out.println("Opcion Invalida!!!");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, introduce solo números.\n");
                teclado.next(); // Limpiar la entrada no válida
            }
        }
        teclado.close();
    }

    //        Busqueda de libro por nombre en la base de datos
    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        Datos busquedaNombre = convierteDatos.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBusqueda = busquedaNombre.resultado().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libroBusqueda.isPresent()) {
            System.out.println("\n     ***Libro Encontrado!***");
            System.out.println("Titulo: " + libroBusqueda.get().titulo());
            System.out.println("Autor: " + libroBusqueda.get().autor().get(0).nombre());
            System.out.println("Idioma: " + libroBusqueda.get().idiomas().get(0));
            System.out.println("Descargas: " + libroBusqueda.get().numeroDeDescargas());
            var datosLibros = libroBusqueda.get();
            Libro libro = new Libro(datosLibros);

            Autor autor = new Autor(datosLibros.autor().get(0));
            System.out.println("--------------" + autor);
//            Optional<Autor> autorOptional = repositoryA.findByNombre(autor.getNombre());
//            libro.setAutor(autorOptional.get());
            repositoryA.save(autor);
//            libro.setAutor(autor);
            repository.save(libro);
        } else {
            System.out.println("Libro No Encontrado!!");
        }
        System.out.println("\n--------------------------------");
    }

    private void listaLibros() {
        List<Libro> listarLibros = new ArrayList<>();
        listarLibros = repository.findAll();
        listarLibros.stream().forEachOrdered(l->{
            System.out.println("TITULO :"+l.getTitulo()!= null ? l.getTitulo():"N/A");
            System.out.println("AUTOR: "+l.getAutor().getNombre()!= null ?l.getAutor():"N/A");
            System.out.println("IDIOMA: "+l.getIdiomas()!= null ?l.getIdiomas():"N/A");
            System.out.println("CANT. DE DESCARGAS: "+l.getNumeroDeDescargas()!= null ?l.getNumeroDeDescargas():"N/A");
            System.out.println("-----------------------------");
        });
    }

//    private void getDatosLibros() {
//        System.out.println("Ingrede el nombre del libro que desea buscar: ");
//        var nombreLibro = teclado.nextLine();
//        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", ""));
//        Datos datos= convierteDatos.obtenerDatos(json, Datos.class);
//        var dato = datos.toString();
//        Libros libroClass = new Libros(dato);
//        repository.save(libroClass);
//    }
}
