package streams;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperation {
    public static void main(String[] args) {

        List<Integer> edades = Arrays.asList(23,45,12,67,34,89,22,90,11);
        Predicate<Integer> esMayorDeEdad = edad -> edad >= 18;

        Double promedioMayordeEdad = edades.stream()
                .filter(esMayorDeEdad)
                .collect(Collectors.averagingDouble(value->value));

        List<Integer> mayorDeEdad = edades.stream()
                .filter(esMayorDeEdad)
                .collect(Collectors.toList());

        ArrayList<Integer> mayoresEdad = edades.stream()
                .filter(esMayorDeEdad)
                .collect(Collectors.toCollection(ArrayList::new));


        System.out.println("El promedio de edad de los mayores de edad es: " + promedioMayordeEdad);
        System.out.printf("El promedio de edad de los mayores de edad es: %.2f%n", promedioMayordeEdad);

        //Map: Transforma los elementos de una secuencia en otros elementos
        //Ejemplo: Obtener una lista con el doble de cada número

        //Flatmap: Aplana una secuencia de secuencias en una sola secuencia
        //Ejemplo: Obtener una lista con todas las letras de una lista de palabras



        List<String> frases = Arrays.asList("Hola mundo", "Java es genial", "Streams son poderosos");

        List<String> palabras = frases.stream()
                .flatMap(frase -> Arrays.stream(frase.split(" ")))
                .collect(Collectors.toList());

        System.out.println(palabras);

        Function<String, Stream<String>> mapaAStream = frase -> Arrays.stream(frase.split(" "));
        List<String> palabras2 = frases.stream()
                .flatMap(mapaAStream)
                .collect(Collectors.toList());

        System.out.println(palabras2);

        //Collectors
        List<String> nombres = Arrays.asList("Ana", "Luis", "Carlos", "Marta", "Lucía");
        List<String> nombresMayusculas = nombres.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(nombresMayusculas);

        //Joining
        String nombresConcatenados = nombres.stream()
                .collect(Collectors.joining(", "));

        System.out.println(nombresConcatenados);

        //GroupingBy
/*        List<Persona> personas = Arrays.asList(
                new Persona("Ana", 23),
                new Persona("Luis", 30),
                new Persona("Carlos", 25),
                new Persona("Marta", 30),
                new Persona("Lucía", 23)
        );

        var personasPorEdad = personas.stream()
                .collect(Collectors.groupingBy(Persona::getEdad));*/

        //List<Integer,List<String>> agrupados = nombres.stream()
        //        .collect(Collectors.groupingBy(nombre -> nombre.length(), Collectors.toList()));


        //System.out.println(agrupados);


        //Combinando operaciones
        nombres.stream()
                .filter(s->s.length()>3)
                .map(String::toUpperCase)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

    }

}
