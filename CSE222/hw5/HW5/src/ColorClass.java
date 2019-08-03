/**
 * @author Can BEYAZNAR - 161044038
 *
 */

public class ColorClass implements Comparable<ColorClass> {

    private int Red;
    private int Green;
    private int Blue;
    private double NormOfColor;

    public ColorClass() {
        Red=0;
        Green=0;
        Blue=0;
        NormOfColor=0;
    }

    public ColorClass(int r, int g, int b) {
        Red=r;
        Green=g;
        Blue=b;
        NormOfColor = sqrt(((Red*Red) + (Green*Green)+(Blue*Blue)));

    }

    public ColorClass(ColorClass temp) {
        Red=temp.Red;
        Green=temp.Green;
        Blue=temp.Blue;
        NormOfColor=temp.NormOfColor;
    }

    private double sqrt(double val) //for the norm of colors
    {
        if (val == 0)
            return 0;

        double last = 0.0;
        double result = 1.0;
        while (result != last) {
            last = result;
            result = ((result + val / result) / 2);
        }
        return result;
    }

    public int getRed() {
        return Red;
    }

    public int getGreen() {
        return Green;
    }

    public int getBlue() {
        return Blue;
    }

    public double getNormOfColor() {
        return NormOfColor;
    }

    @Override
    public int compareTo(ColorClass input) {

        if(Red > input.getRed())
            return 1;
        else if(Red == input.getRed())
        {
            if(Green > input.getGreen())
                return 1;

            else if(Green == input.getGreen())
            {
                if(Blue > input.getBlue())
                    return 1;
                else if(Blue == input.getBlue())
                    return 0;
                else
                    return -1;
            }
            else
                return -1;
        }
        else
            return -1;
    }
    public String toString(){
        String str = new String();
        str = " Red: " + getRed() + "  Green: " + getGreen() + " Blue: " + getBlue();
        return str;
    }
}
