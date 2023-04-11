import java.util.regex.Pattern;

public class BinaryRegexp {

    public static Pattern multipleOf3() {
        // Regular expression that matches binary inputs that are multiple of 3
        System.out.println("a");
        return Pattern.compile("^((0)*(1(0(1)*0)*1)*)+$");
    }

}
