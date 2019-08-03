/**
 * @author Can BEYAZNAR - 161044038
 *
 */

import java.util.Comparator;

public class myLEX implements Comparator<ColorClass> {

    @Override
    public int compare(ColorClass firstInput, ColorClass secondInput) {
        return firstInput.compareTo(secondInput);
    }
}
