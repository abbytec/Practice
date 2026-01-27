import java.util.ArrayList;
import java.util.List;

public class ObservedPin {
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

} // ObservedPin
