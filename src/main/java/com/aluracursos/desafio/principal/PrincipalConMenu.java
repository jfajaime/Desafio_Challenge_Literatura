package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.model.Datos;
import com.aluracursos.desafio.model.DatosLibros;
import com.aluracursos.desafio.service.ConsumoAPI;
import com.aluracursos.desafio.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class PrincipalConMenu {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private static final String URL_BASE = "https://gutendex.com/books/";
    Scanner teclado = new Scanner(System.in);

    public void muestraMenu() {
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            String menu = """
                    1 - Buscar libros por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
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
        System.out.println("Ingrede el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", ""));
        Datos busquedaNombre = convierteDatos.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBusqueda = busquedaNombre.resultado().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libroBusqueda.isPresent()) {
            System.out.println("\n     ***Libro Encontrado!***\n");
            System.out.println("Titulo: "+libroBusqueda.get().titulo());
            System.out.println("Autor: "+libroBusqueda.get().autor().get(0).nombre());
            System.out.println("Idioma: "+libroBusqueda.get().idiomas().get(0));
            System.out.println("Descargas: "+libroBusqueda.get().numeroDeDescargas());

        } else {
            System.out.println("Libro No Encontrado!!");
        }
        System.out.println("\n--------------------------------");
    }
}
