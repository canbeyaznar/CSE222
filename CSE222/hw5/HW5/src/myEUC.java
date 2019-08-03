/**
 * @author Can BEYAZNAR - 161044038
 *
 */

import java.util.Comparator;

public class myEUC implements Comparator<ColorClass> {

    @Override
    public int compare(ColorClass firstInput, ColorClass secondInput) {
        if(firstInput.getNormOfColor() > secondInput.getNormOfColor())
            return 1;
        else if(firstInput.getNormOfColor() == secondInput.getNormOfColor())
            return 0;
        else
            return -1;
    }
}
