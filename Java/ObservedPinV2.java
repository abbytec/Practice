import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ObservedPinV2 {
    public static List<String> adjMatrix = List.of(
            "08", // 0
            "124", // 1
            "1253", // 2
            "236", // 3
            "1457", // 4
            "24568", // 5
            "3569", // 6
            "478", // 7
            "57890", // 8
            "689" // 9
    );

    public static List<String> getPINs(String observed) {
        if (observed.length() == 0)
            return List.of("");
        List<String> pins = new ArrayList<>();
        adjMatrix.get(Character.getNumericValue(observed.charAt(0)))
                .chars().forEach(c -> {
                    getPINs(observed.substring(1)).forEach(comb -> {
                        pins.add(Character.getNumericValue(c) + comb);
                    });
                });
        return pins;
    } // getPINs

    public static BigInteger countPossibilities(String observed) {
        BigInteger total = BigInteger.ONE; // Iniciamos en 1 (neutro multiplicativo)

        for (char c : observed.toCharArray()) {
            int digit = Character.getNumericValue(c);
            int variations = adjMatrix.get(digit).length();

            // Multiplicamos el acumulado por las variaciones de este dígito
            total = total.multiply(BigInteger.valueOf(variations));
        }

        return total;
    }

    public static void main(String[] args) {
        String pin = "53534529423412938123088523478543534198471634972364519214053534529423412938123088523478543534198471634972364519214053534529423412938123088523478543534198471634972364519214010535345294234129381230885234785435341984716349723645192140";
        System.out.println("Total de combinaciones posibles: " + countPossibilities(pin));
        pin = "0791";
        System.out.println("PINs: " + getPINs(pin));
    }

} // ObservedPin
