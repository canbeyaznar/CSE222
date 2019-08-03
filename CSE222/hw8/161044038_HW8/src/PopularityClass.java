/**
 * @author Can BEYAZNAR 161044038
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class PopularityClass{

    private ListGraph graph;

    private int NumberOfPeople;
    private int NumberOfOrderedRelation;

    public PopularityClass(File text) throws FileNotFoundException {

        Scanner reader = new Scanner(text);

        NumberOfPeople = reader.nextInt();
        NumberOfOrderedRelation = reader.nextInt();

        graph = new ListGraph(NumberOfPeople, true);

        int SRC, DEST;
        while (reader.hasNext()){
            SRC = reader.nextInt();
            DEST = reader.nextInt();

            graph.insert(new Edge(SRC,DEST));
        }

    }

    public int findPopularNum(){

        int[] PointedElements = new int[NumberOfPeople];
        int i;

        for(i=0; i<NumberOfPeople; i++ )
            PointedElements[i] = 0;

        for(i=1; i<=NumberOfPeople; i++)
            for(int j=1; j<=NumberOfPeople; j++)
                if ( ( i != j ) && findPath(i,j) )
                    PointedElements[j-1]++;

        int Result=0;

        for(i=0; i<NumberOfPeople; i++)
            if(PointedElements[i] >= NumberOfPeople-1)
                Result++;

        return Result;
    }

    //this method will return true if there is path between SRC and DEST...
    private boolean findPath(int firstInt, int secondInt){

        boolean travelledElements[] = new boolean[NumberOfPeople+1];
        myLinkedList<Integer> myList = new myLinkedList<>();

        travelledElements[firstInt] = true;
        myList.insert(firstInt);

        while (!myList.isEmpty()){

            firstInt = myList.poll();
            Iterator<Edge> Iter = graph.edgeIterator(firstInt);

            while ( Iter != null && Iter.hasNext()){

                Edge temp = Iter.next();

                if(temp.getDest() == secondInt)
                    return true;

                if (!travelledElements[temp.getDest()]){
                    travelledElements[temp.getDest()] = true;
                    myList.insert(temp.getDest());
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return graph.toString();
    }
}