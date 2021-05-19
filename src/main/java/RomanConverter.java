import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RomanConverter {

    enum RomanLiteral {
        I(1),
        IV(4),
        V(5),
        IX(9),
        X(10),
        XL(40),
        L(50),
        XC(90),
        C(100),
        CD(400),
        D(500),
        CM(900),
        M(1000);

        int value;

        RomanLiteral(int value) {
            this.value = value;
        }

        public static List<RomanLiteral> literalsDescending() {
            List<RomanLiteral> literals = Arrays.asList(RomanLiteral.values());
            Collections.reverse(literals);

            return literals;
        }
    }

    public String toRoman(int latinNumber) {
        StringBuilder result = new StringBuilder();

        int rest = latinNumber;

        for (RomanLiteral literal : RomanLiteral.literalsDescending()) {
            while (rest >= literal.value) {
                result.append(literal.name());
                rest -= literal.value;
            }
        }

        return result.toString();
    }
}
