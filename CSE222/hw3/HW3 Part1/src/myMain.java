/**
 * @author Can BEYAZNAR - 161044038
 */

import java.io.*;

public class myMain {

    public static void main(String args[]) throws Exception {

        System.out.println("Test 1 :");
        File file1 = new File("src\\test1.txt");
        readFile(file1);
        System.out.println("-o-o-o-o-o-o-");

        System.out.println("Test 2 :");
        File file2 = new File("src\\test2.txt");
        readFile(file2);

    }

    public static void readFile(File file) throws Exception
    {
        BufferedReader myBuffer = new BufferedReader(new FileReader(file));

        int column=0, row=0;
        String myStr;
        while ((myStr = myBuffer.readLine()) != null)
        {
            if(myStr!=null)
                column = (myStr.length()/2) + (myStr.length()%2);
            row++;
        }
        char myTestArr[][] = new char[row][column];

        myBuffer.close();

        myBuffer = new BufferedReader(new FileReader(file));
        myStack<Character> deneme = new myStack<Character>();
        for(int i=0; i<row; i++)
        {
            myStr = myBuffer.readLine();
            for(int j=0; j<column; j++)
            {
                if(j*2 >= myStr.length() )
                    myTestArr[i][j] = (char) myStr.charAt((j*2)-1);
                else
                    myTestArr[i][j] = myStr.charAt(j*2);

                deneme.push(myTestArr[i][j]);
            }
        }

        beginTest(myTestArr, row,column);
    }

    /**
     *
     * @param myArr 2D Array
     * @param row      2D Array's row
     * @param column    2D Array's column
     */
    public static void beginTest(char myArr[][], int row, int column)
    {
        myStack<ElementNeighbour> elementStack = new myStack<ElementNeighbour>();
        int Count=0;
        for(int y=0; y<row; y++)
        {
            for(int x=0; x<column; x++)
            {

                if(myArr[y][x] == '1' )
                {
                    ElementNeighbour firstTemp = new ElementNeighbour(myArr[y][x],x,y,false );
                    elementStack.push(firstTemp);

                    while(!(elementStack.empty()))
                    {
                        boolean isAdded=false;
                        ElementNeighbour storedElement = new ElementNeighbour(elementStack.peek());

                        if( ( storedElement.getLoc_Y()-1)>=0 && (myArr[storedElement.getLoc_Y()-1][storedElement.getLoc_X()] == '1') )
                        {
                            ElementNeighbour secondTemp = new ElementNeighbour();
                            secondTemp.setData( myArr[storedElement.getLoc_Y()-1][storedElement.getLoc_X()] );
                            secondTemp.setLoc_X(storedElement.getLoc_X());
                            secondTemp.setLoc_Y(storedElement.getLoc_Y()-1);
                            myArr[storedElement.getLoc_Y()-1][storedElement.getLoc_X()] = 'X';
                            secondTemp.setUsed(true);

                            elementStack.push(secondTemp);
                            isAdded=true;
                        }

                        if( ( storedElement.getLoc_X()-1)>=0 && (myArr[storedElement.getLoc_Y()][storedElement.getLoc_X()-1] == '1'))
                        {
                            ElementNeighbour secondTemp = new ElementNeighbour();
                            secondTemp.setData( myArr[storedElement.getLoc_Y()][storedElement.getLoc_X()-1] );
                            secondTemp.setLoc_X(storedElement.getLoc_X()-1);
                            secondTemp.setLoc_Y(storedElement.getLoc_Y());
                            myArr[storedElement.getLoc_Y()][storedElement.getLoc_X()-1] = 'X';
                            secondTemp.setUsed(true);

                            elementStack.push(secondTemp);
                            isAdded=true;
                        }

                        if( ( storedElement.getLoc_Y()+1)<row && (myArr[storedElement.getLoc_Y()+1][storedElement.getLoc_X()] == '1') )
                        {
                            ElementNeighbour secondTemp = new ElementNeighbour();
                            secondTemp.setData( myArr[storedElement.getLoc_Y()+1][storedElement.getLoc_X()] );
                            secondTemp.setLoc_X(storedElement.getLoc_X());
                            secondTemp.setLoc_Y(storedElement.getLoc_Y()+1);
                            myArr[storedElement.getLoc_Y()+1][storedElement.getLoc_X()] = 'X';
                            secondTemp.setUsed(true);

                            elementStack.push(secondTemp);
                            isAdded=true;
                        }

                        if( ( storedElement.getLoc_X()+1)<column && (myArr[storedElement.getLoc_Y()][storedElement.getLoc_X()+1] == '1'))
                        {
                            ElementNeighbour secondTemp = new ElementNeighbour();
                            secondTemp.setData( myArr[storedElement.getLoc_Y()][storedElement.getLoc_X()+1] );
                            secondTemp.setLoc_X(storedElement.getLoc_X()+1);
                            secondTemp.setLoc_Y(storedElement.getLoc_Y());
                            myArr[storedElement.getLoc_Y()][storedElement.getLoc_X()+1] = 'X';
                            secondTemp.setUsed(true);

                            elementStack.push(secondTemp);
                            isAdded=true;
                        }

                        if(isAdded == false && elementStack.peek().getUsed()==true)
                            elementStack.pop();

                        else if(firstTemp == elementStack.peek() && isAdded == false )
                            elementStack.pop();
                    }
                    Count++;
                }
            }
        }
        System.out.println("Result: "+Count);
    }

}
