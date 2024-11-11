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
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                                        
                    0 - Salir                    
                    - Ingrese la opcion deseada a continuacion: !
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

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

        }

    }

    //        Busqueda de libro por nombre en la base de datos
    private void buscarLibroPorTitulo() {
        System.out.println("Ingrede el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", ""));
        var busquedaNombre = convierteDatos.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBusqueda = busquedaNombre.resultado().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libroBusqueda.isPresent()) {
            System.out.println("Libro Encontrado!");
            System.out.println(libroBusqueda);
        } else {
            System.out.println("Libro No Encontrado!!");
        }
        System.out.println("--------------------------------");
    }
}
