/**
 * @author Can Beyaznar 161044038
 */

public class myMathClass {

    public double abs(double val)
    {
        if(val < 0)
            val*=-1;
        return val;
    }

    public double factorial(int n)
    {
        double Result = 1;
        for(int i=1; i<=n; i++)
            Result = Result*i;
        return Result;
    }

    public double pow(double val, int n)
    {
        double Result = 1;
        for(int i=0; i<n; i++)
            Result = Result * val;
        return Result;
    }


    public double sin(double degree)
    {
        double PI=3.14159;
        double rad=(PI*degree)/180;
        double Result=rad;
        int control=-1;
        for(int i=1; i<10; i++)
        {
            Result = Result + control * ( pow(rad,(2*i)+1)/factorial((2*i)+1) );
            control*=-1;
        }

        return Result;
    }

    public double cos(double degree) {

        double PI=3.14159;
        double rad=(PI*degree)/180;
        double Result=1;
        int control=-1;
        for(int i=1; i<10; i++)
        {
            Result = Result + control * ( pow(rad,(2*i))/factorial((2*i)) );
            control*=-1;
        }

        return Result;

    }

}
