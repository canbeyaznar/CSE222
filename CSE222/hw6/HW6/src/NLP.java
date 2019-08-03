/**
 * @author Can BEYAZNAR - 161044038
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NLP
{
    public Word_Map wmap = new Word_Map();
    private int totalNumberOfFile = 0;
    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    /*Reads the dataset from the given dir and created a word map */
    public void readDataset(String dir)
    {
        File Directory = new File(dir);
        File[] fileNames = Directory.listFiles();
        totalNumberOfFile = fileNames.length;

        String datasOfFile = new String();

        for(File file : fileNames){
            if(file.isDirectory())
                readDataset(file.toString());
            else {
                try {
                    datasOfFile = datasOfFile + readContent(file);
                    String word = datasOfFile.trim().replaceAll("\\p{Punct}", "");
                    word = word.trim().replaceAll("\n", " ");
                    word = word.trim().replaceAll("     ", " ");
                    word = word.trim().replaceAll("    ", " ");
                    word = word.trim().replaceAll("   ", " ");
                    word = word.trim().replaceAll("  ", " ");

                    replaceWords(word, file.toString());

                    datasOfFile = new String();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readContent(File file) throws IOException{
        String totalString = new String();
        try(BufferedReader br  = new BufferedReader(new FileReader(file))){
            String strLine;
            while((strLine = br.readLine()) != null)
                totalString += strLine + "\n";
        }
        return totalString;
    }



    private void replaceWords(String mySTR, String fileName ){

        String[] words = mySTR.split(" ");

        int countOfWords = 0;
        for (String readWords : words)
        {
            File_Map tempFileMap = new File_Map();

            if(!wmap.isEmpty() && wmap.containsKey(readWords) ){
                tempFileMap = (File_Map) wmap.get(readWords);
                ArrayList<Integer> myArrList = (ArrayList<Integer>) tempFileMap.get(fileName);

                if(!tempFileMap.containsKey(fileName))
                {
                    myArrList = new ArrayList<Integer>();
                    myArrList.add(countOfWords);
                    tempFileMap.put(fileName,myArrList);
                }

                else
                {
                    myArrList.add(countOfWords);
                    tempFileMap.put(fileName,myArrList);
                }

                wmap.put(readWords,tempFileMap);
            }

            else
            {
                ArrayList<Integer> myArrList = new ArrayList<Integer>();
                myArrList.add(countOfWords);
                tempFileMap.put(fileName,myArrList);
                wmap.put(readWords,tempFileMap);
            }
            countOfWords++;
        }
    }


    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word) throws IOException {

        List<String> Result = new ArrayList<String>();

        if(wmap != null && !wmap.isEmpty() && wmap.containsKey(word))
        {
            File_Map wordFileMap = (File_Map) wmap.get(word);

            TreeSet fileNames = new TreeSet(wordFileMap.keySet());
            Object fileNamesArr[] = fileNames.toArray();

            ArrayList locationsOfWords = new ArrayList(wordFileMap.values());

            for(int i=0; i<fileNamesArr.length; i++)
            {
                File tempFile = new File((String) fileNamesArr[i]);
                String EachStringofFile = readContent(tempFile);
                String trimedWord = EachStringofFile.trim().replaceAll("\\p{Punct}", "");
                trimedWord = trimedWord.trim().replaceAll("\n", " ");
                trimedWord = trimedWord.trim().replaceAll("     ", " ");
                trimedWord = trimedWord.trim().replaceAll("    ", " ");
                trimedWord = trimedWord.trim().replaceAll("   ", " ");
                trimedWord = trimedWord.trim().replaceAll("  ", " ");

                String[] words = trimedWord.split(" ");

                int countOfWords = 0;
                int countOfFindedBigram = 0;

                ArrayList<Integer> locations = (ArrayList<Integer>) locationsOfWords.get(i);
                int j=0;
                while(j<words.length && countOfFindedBigram<locations.size() )
                {
                    if(countOfWords == ((Integer) locations.get(countOfFindedBigram))+1)
                    {
                        String bigramString = word + " " + words[j];
                        if(!Result.contains(bigramString))
                            Result.add(bigramString);
                        countOfFindedBigram++;
                    }
                    countOfWords++;
                    j++;
                }

            }
        }
        return Result;
    }


    /*Calculates the tfIDF value of the given word for the given file */
    public float tfIDF(String word, String fileName) throws IOException {
        float Result = 0f;
        float TF = 0f;
        float IDF = 0f;

        if(wmap != null && !wmap.isEmpty() && wmap.containsKey(word))
        {
            File_Map myFileMap = (File_Map) wmap.get(word);

            if(myFileMap.containsKey("dataset\\"+fileName))
            {
                ArrayList wordOfLocations = (ArrayList) myFileMap.get("dataset\\"+fileName);

                File tempFile = new File((String) "dataset\\"+fileName);
                String EachStringofFile = readContent(tempFile);
                String trimedWord = EachStringofFile.trim().replaceAll("\\p{Punct}", "");
                trimedWord = trimedWord.trim().replaceAll("\n", " ");
                trimedWord = trimedWord.trim().replaceAll("     ", " ");
                trimedWord = trimedWord.trim().replaceAll("    ", " ");
                trimedWord = trimedWord.trim().replaceAll("   ", " ");
                trimedWord = trimedWord.trim().replaceAll("  ", " ");

                String[] words = trimedWord.split(" ");
                TF = ((float)wordOfLocations.size()) / ((float)words.length);
                IDF = (float) Math.log((float)(totalNumberOfFile) / ((float) myFileMap.values().size()));

                Result = TF*IDF;
            }
        }

        return Result;
    }

    /*Print the WordMap by using its iterator*/
    public  void printWordMap()
    {
        Iterator temp = wmap.iterator();
        while (temp.hasNext())
            System.out.print(temp.next() + " " );
        System.out.println();
    }

    public String toString(){
        return wmap.toString();
    }

}



