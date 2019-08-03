/**
 * @author Can BEYAZNAR 161044038
 */

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {

        PopularityClass TEST = new PopularityClass(new File("src/input.txt"));

        System.out.println(TEST);

        int Result = TEST.findPopularNum();
        System.out.println(Result);
    }

}
