/**
 * @author Can BEYAZNAR - 161044038
 *
 */

import java.util.Comparator;

public class myBMX implements Comparator<ColorClass> {

    @Override
    public int compare(ColorClass firstInput, ColorClass secondInput) {
        int firstRedBinary = decimalToBinary(firstInput.getRed()) ;
        int firstGreenBinary = decimalToBinary(firstInput.getGreen());
        int firstBlueBinary = decimalToBinary(firstInput.getBlue());

        int secondRedBinary = decimalToBinary(secondInput.getRed());
        int secondGreenBinary = decimalToBinary(secondInput.getGreen());
        int secondBlueBinary = decimalToBinary(secondInput.getBlue());


        int firstRedArr[] = new int[8];
        int firstGreenArr[] = new int[8];
        int firstBlueArr[] = new int[8];

        int secondRedArr[] = new int[8];
        int secondGreenArr[] = new int[8];
        int secondBlueArr[] = new int[8];

        for (int i=7; i>=0; i--)
        {
            firstRedArr[i] = firstRedBinary%10;
            firstRedBinary = ( firstRedBinary - (firstRedBinary%10) ) / 10;

            firstGreenArr[i] = firstGreenBinary%10;
            firstGreenBinary = ( firstGreenBinary - (firstGreenBinary%10) ) / 10;

            firstBlueArr[i] = firstBlueBinary%10;
            firstBlueBinary = ( firstBlueBinary - (firstBlueBinary%10) ) / 10;

            secondRedArr[i] = secondRedBinary%10;
            secondRedBinary = ( secondRedBinary - (secondRedBinary%10) ) / 10;

            secondGreenArr[i] = secondGreenBinary%10;
            secondGreenBinary = ( secondGreenBinary - (secondGreenBinary%10) ) / 10;

            secondBlueArr[i] = secondBlueBinary%10;
            secondBlueBinary = ( secondBlueBinary - (secondBlueBinary%10) ) / 10;
        }

        int FINAL_firstArr[] = new int[24];
        int FINAL_secondArr[] = new int[24];

        int currentIndex = 0;
        int i = 0;
        while(i<24)
        {
            FINAL_firstArr[i] = firstRedArr[currentIndex];
            FINAL_firstArr[i+1] = firstGreenArr[currentIndex];
            FINAL_firstArr[i+2] = firstBlueArr[currentIndex];

            FINAL_secondArr[i] = secondRedArr[currentIndex];
            FINAL_secondArr[i+1] = secondGreenArr[currentIndex];
            FINAL_secondArr[i+2] = secondBlueArr[currentIndex];

            currentIndex++;
            i += 3;
        }

        int FINAL_FirstDecimal=0;
        int FINAL_SecondDecimal=0;

        for(i=0; i<24; i++)
        {
            FINAL_FirstDecimal = FINAL_FirstDecimal + pow(10,(23-i)) * FINAL_firstArr[i];
            FINAL_SecondDecimal = FINAL_SecondDecimal + pow(10,(23-i)) * FINAL_secondArr[i];
        }

        int FINAL_FirstBinary = decimalToBinary(FINAL_FirstDecimal);
        int FINAL_SecondBinary = decimalToBinary(FINAL_SecondDecimal);

        if(FINAL_FirstBinary > FINAL_SecondBinary)
            return 1;
        else if(FINAL_FirstBinary == FINAL_SecondBinary)
            return 0;
        else
            return -1;
    }

    private int[] convertToArr(int input){
        int Result[] = new int[8];
        for(int i=7; i<=0; i--)
        {
            Result[i] = input%10;
            input = ( input - (input%10) ) / 10;
        }
        return Result;
    }

    private int decimalToBinary(int input){

        int Result = 0;
        int degree = 1;
        while (input != 0)
        {
            Result = Result + (input%2) * degree;
            input /= 2;
            degree *= 10;
        }
        return Result;

    }

    private int binaryToDecimal(int input) {

        int Result = 0;
        int degree = 0;
        while(input != 0){
            if(input%10 == 1)
                Result = Result + pow(2,degree);
            input = ( input - (input%10) ) / 10;
            degree++;
        }
        return Result;
    }

    private int pow(int num, int degree) {
        int Result = 1;
        for(int i=0; i<degree; i++)
            Result *= num;
        return Result;
    }

}
