/**
 * @author Can BEYAZNAR - 161044038
 *
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MyHashMap {

    public static void main(String args[]) throws IOException {

        NLP x = new NLP();
        x.readDataset("dataset");

        readFile(x,"./src/input.txt");

        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-");
        System.out.println("printWordMap Method : ");
        x.printWordMap();
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-");
    }

    public static void readFile(NLP Input_NLP, String FileName) throws IOException {

        File myFile = new File(FileName);
        Scanner reader = new Scanner(myFile);

        while (reader.hasNext()) {
            String word  = reader.next();

            if(word.contains("bigram")){
                word  = reader.next();
                System.out.println(Input_NLP.bigrams(word));
            }

            else if(word.contains("tfidf")){

                word  = reader.next();
                String tempWord = reader.next();
                System.out.println(Input_NLP.tfIDF(word,tempWord));
            }
        }

    }
}
