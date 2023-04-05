
// Debes imprimir la cantidad de veces que se repite una palabra y la palabra usando menos de 15 lineas de codigo
import java.util.Arrays;
import java.util.Collections;

public class App {
    public static void main(String[] args) throws Exception {
        var palabras = Arrays.asList("hola que tal tal".split(" "));
        palabras.stream().distinct().forEach((string) -> {
            System.out.println(string + " " + Collections.frequency(palabras, string));
        });
    }
}