//package com.aluracursos.desafio.principal;
//
//import com.aluracursos.desafio.model.Datos;
//import com.aluracursos.desafio.model.DatosLibros;
//import com.aluracursos.desafio.service.ConsumoAPI;
//import com.aluracursos.desafio.service.ConvierteDatos;
//
//import java.util.Comparator;
//import java.util.DoubleSummaryStatistics;
//import java.util.Optional;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//
//public class Principal {
//    private ConsumoAPI consumoAPI = new ConsumoAPI();
//    private ConvierteDatos convierteDatos = new ConvierteDatos();
//    private static final String URL_BASE = "https://gutendex.com/books/";
//    Scanner teclado = new Scanner(System.in);
//
//    public void muestraMenu(){
//        var json = consumoAPI.obtenerDatos(URL_BASE);
//        System.out.println("formato json: "+json);
//
//        System.out.println("--------------------------------");
//
//        var datos = convierteDatos.obtenerDatos(json, Datos.class);
//        System.out.println("formato convertido: "+datos);
//
//        System.out.println("--------------------------------");
//
////        top 10
//        System.out.println("Top 10 libros mas descargados");
//        datos.resultado().stream()
//                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
//                .limit(10)
//                .map(l->l.titulo().toUpperCase())
//                .forEach(System.out::println);
//        System.out.println("--------------------------------");
//
////        Busqueda de libro por nombre
//        System.out.println("Ingrede el nombre del libro que desea buscar: ");
//        var nombreLibro = teclado.nextLine();
//        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ",""));
//        var busquedaNombre = convierteDatos.obtenerDatos(json, Datos.class);
//        Optional<DatosLibros> libroBusqueda = busquedaNombre.resultado().stream()
//                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
//                .findFirst();
//        if (libroBusqueda.isPresent()) {
//            System.out.println("Libro Encontrado!");
//            System.out.println(libroBusqueda);
//        }else {
//            System.out.println("Libro No Encontrado!!");
//        }
//        System.out.println("--------------------------------");
//
////trabajando con estadisticas
//        DoubleSummaryStatistics estadisticas = datos.resultado().stream().filter(d -> d.numeroDeDescargas() > 0)
//                .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas));
//        System.out.println("Promedio de descaragas > " + estadisticas.getAverage());
//        System.out.println("Maximo de descaragas > " + estadisticas.getMax());
//        System.out.println("Minimo de descaragas > " + estadisticas.getMin());
//        System.out.println("Cantidad de registros evaluados > " + estadisticas.getCount());
//
////        buscar libros entre fechas
//    }
//}
