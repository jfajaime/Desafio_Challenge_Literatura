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
                        ListarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosPorFecha();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
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

    //        Busqueda de libro por nombre
    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        Datos busquedaNombre = convierteDatos.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBusqueda = busquedaNombre.resultado().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst().map(libro -> new DatosLibros(Optional.ofNullable(libro.titulo()).orElse("Título Desconocido"),
                        Optional.ofNullable(libro.autor()).orElse(List.of()),
                        Optional.ofNullable(libro.idiomas()).orElse(List.of("Idioma desconocido")),
                        Optional.ofNullable(libro.numeroDeDescargas()).orElse((double) 0)));

        if (libroBusqueda.isPresent()) {
            System.out.println("\n     ***Libro Encontrado!***");
            System.out.println("Titulo: " + libroBusqueda.get().titulo());
            System.out.println("Autor: " + libroBusqueda.get().autor().get(0).nombre());
            System.out.println("Idioma: " + libroBusqueda.get().idiomas().get(0));
            System.out.println("Descargas: " + libroBusqueda.get().numeroDeDescargas());
            var datosLibros = libroBusqueda.get();
            Libro libro = new Libro(datosLibros);
//autorOptional existe en la base de datos???
            Autor autor = new Autor(datosLibros.autor().get(0));
            System.out.println("--------------");
            Optional<Autor> autorOptional = repositoryA.findByNombre(autor.getNombre());
            if (autorOptional.isPresent()) {
                libro.setAutor(autorOptional.get());
            } else {
                repositoryA.save(autor);
                libro.setAutor(autorOptional.get());
            }
            Optional<Libro> libroOptional = repository.findByTituloContainsIgnoreCase(libro.getTitulo());
            if (libroOptional.isPresent()) {
                System.out.println(";) Este titulo ya se encuentra en la base de datos local");
            } else {
                repository.save(libro);
            }
        } else {
            System.out.println("Libro No Encontrado :( ");
        }
        System.out.println("\n--------------------------------");
    }

    private void listaLibros() {
        List<Libro> listarLibros = new ArrayList<>();
        listarLibros = repository.findAll();
        listarLibros.stream().forEachOrdered(l -> {
            System.out.println("TITULO :" + l.getTitulo());
            System.out.println("AUTOR: " + l.getAutor().getNombre());
            System.out.println("IDIOMA: " + l.getIdiomas());
            System.out.println("CANT. DE DESCARGAS: " + l.getNumeroDeDescargas());
            System.out.println("-----------------------------");
        });
    }

    private void ListarAutoresRegistrados() {
        var autores = repositoryA.findAll();
        autores.stream().forEach(a -> {
            System.out.println(a.toString());
            System.out.println("-------------------\n");
        });
    }
    private void listarAutoresVivosPorFecha() {
        System.out.println("Ingrese el año");
        var año = teclado.nextInt();
        var respuesta = repositoryA.listarAutoresVivosPorFecha(año);
        if (respuesta.isEmpty()) {
            System.out.println("No se encontraron autores vivos para el año selecionado :(\n");
        } else {
            System.out.println("Autores activos ;) en el año: "+año+"\n");
            List<Autor> autorVivo = respuesta;
            autorVivo.forEach(System.out::println);
            System.out.println("\n-----------------------");
        }
    }
    private void listarLibrosPorIdioma() {
        var idiomas="null";
        System.out.println("Elija el idioma de busqueda:");
        var menu = """
                1 - Español
                2 - Ingles
                2 - Portugues
                3 - Frances
                """;
        System.out.println(menu);
        var lenguaje = teclado.nextInt();
        switch (lenguaje) {
            case 1:
                idiomas = "es";
                break;
            case 2:
                idiomas = "en";
                break;
            case 3:
                idiomas = "pr";
                break;
            case 4:
                idiomas = "fr";
                break;
            default:
                System.out.println("Opcion incorrecta!");
        }
        List<Libro> libroPorIdioma = repository.findByIdiomas(idiomas);
        System.out.println("Lista de libros en : "+idiomas+"\n");
        libroPorIdioma.forEach(libro -> System.out.println(libro.getTitulo()+"\n-------------------------"));
    }


}
