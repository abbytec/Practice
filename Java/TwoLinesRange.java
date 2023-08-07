import java.util.stream.IntStream;

public class TwoLinesRange {
    public static void main(String[] args) {
        System.out.println("Introduce un numero: ");
        IntStream.range(0, Integer.parseInt(System.console().readLine()) + 1).forEach(System.out::println);
    }
}