import java.util.Iterator;

public class myElements {

    private int lengthX=0;
    private int lengthY=0;
    private boolean isUsed[][];
    private int myNums[][];
    private int locX;
    private int locY;
    private int parsedArray[];
    private int countofAssignedInt;
    private int currenLocationParsedArr;
    private char direction;
    private myIterator iter;

    public myElements(int arr[][])
    {
        lengthX=arr[0].length;
        lengthY=arr.length;
        isUsed = new boolean[lengthY][lengthX];
        myNums = new int[lengthY][lengthX];
        parsedArray = new int[lengthY*lengthX];
        countofAssignedInt=0;
        currenLocationParsedArr=0;
        direction='R';
        locX=0;
        locY=0;

        for(int i=0; i<lengthY; i++){
            for(int j=0; j<lengthX; j++)
            {
                isUsed[i][j] = false;
                myNums[i][j] = arr[i][j];
            }
        }
    }

    public int parseToIter()
    {
        if(countofAssignedInt == lengthY * lengthX)
        {
            iter = new myIterator(this);
            return 0;
        }

        if(direction == 'R')
        {
            if(locX < lengthX && isUsed[locY][locX] == false)
            {
                parsedArray[countofAssignedInt] = myNums[locY][locX];
                isUsed[locY][locX] = true;
                countofAssignedInt++;
                locX++;
                return parseToIter();
            }

            else
            {
                direction='D';
                locY++;
                locX--;
                return parseToIter();
            }

        }

        else if(direction == 'D')
        {
            if( locY < lengthY && isUsed[locY][locX]==false )
            {
                parsedArray[countofAssignedInt] = myNums[locY][locX];
                isUsed[locY][locX] = true;
                countofAssignedInt++;
                locY++;
                return parseToIter();
            }
            else
            {
                direction='L';
                locY--;
                locX--;
                return parseToIter();
            }
        }

        else if(direction == 'L')
        {

            if (locX >= 0 && isUsed[locY][locX] == false)
            {
                parsedArray[countofAssignedInt] = myNums[locY][locX];
                isUsed[locY][locX] = true;
                countofAssignedInt++;
                locX--;
                return parseToIter();
            }

            else
            {
                direction='U';
                locY--;
                locX++;
                return parseToIter();
            }
        }

        else if (direction == 'U')
        {
            if(locY>=0 && isUsed[locY][locX] == false)
            {
                parsedArray[countofAssignedInt] = myNums[locY][locX];
                isUsed[locY][locX] = true;
                countofAssignedInt++;
                locY--;
                return parseToIter();
            }
            else
            {
                direction='R';
                locY++;
                locX++;
                return parseToIter();
            }
        }
        return 0;
    }

    public void printWithIterator()
    {
        myIterator temp = new myIterator(this);
        while (temp.hasNext())
            System.out.print(temp.next()+" ");
        System.out.println();

    }

    public String toString()
    {
        String temp = new String();
        for(int i=0; i<countofAssignedInt; i++)
            temp = temp + parsedArray[i]+" ";
        return temp;
    }

    private class myIterator implements Iterable, Iterator
    {

        public myIterator(myElements elements)
        {
            currenLocationParsedArr=0;
        }

        @Override
        public Iterator iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            return (currenLocationParsedArr<=parsedArray.length-1);
        }

        @Override
        public Integer next() {
            if (hasNext())
            {
                Integer temp = parsedArray[currenLocationParsedArr];
                currenLocationParsedArr++;
                return temp;
            }
            return null;
        }
    }

}
