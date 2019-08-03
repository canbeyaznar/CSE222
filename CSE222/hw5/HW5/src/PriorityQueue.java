/**
 * @author Can BEYAZNAR - 161044038
 *
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue {

    private ColorClass[] myHeapArr;
    private int size;
    private int capacity;
    private Comparator<ColorClass> comparator;

    public PriorityQueue(Comparator<ColorClass> Input_CompareType){
        comparator = Input_CompareType;
        capacity = 10;
        myHeapArr = new ColorClass[capacity + 1];
        myHeapArr[0] = new ColorClass(999,999,999); // invalid index WILL NOT USE!!
        size = 0;
    }

    private int parent(int index) {
        return (index/2);
    }

    private int leftChild(int index) {
        return ((2*index) );
    }

    private int rightChild(int index) {
        return ( (2*index) + 1);
    }

    private void allocate() {
        capacity *= 2;
        myHeapArr = Arrays.copyOf(myHeapArr,capacity);
    }

    private void swap(int firstIndex, int secondIndex) {
        ColorClass temp = new ColorClass();
        temp = myHeapArr[firstIndex];
        myHeapArr[firstIndex] = myHeapArr[secondIndex];
        myHeapArr[secondIndex] = temp;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty()
    {
        if(size == 0)
            return true;
        return false;
    }

    private void sortMaxHeap(int index){
        if (!(index >=  (size / 2)  &&  index <= size))
        {
            if (  comparator.compare(myHeapArr[index],myHeapArr[leftChild(index)]) == -1  || comparator.compare(myHeapArr[index],myHeapArr[rightChild(index)]) == -1 )
            {
                if ( comparator.compare(myHeapArr[leftChild(index)],myHeapArr[rightChild(index)]) == 1 )
                {
                    swap(index, leftChild(index));
                    sortMaxHeap(leftChild(index));
                }

                else
                {
                    swap(index, rightChild(index));
                    sortMaxHeap(rightChild(index));
                }
            }
        }
    }

    public ColorClass remove()
    {

        if(size == 0)
            throw new NoSuchElementException();

        ColorClass removedElement = new ColorClass(myHeapArr[1]);

        myHeapArr[1] = myHeapArr[size--];
        int index = 1;
        boolean control = true;

        while(index <= size && control)
        {
            boolean control2 = true;

            if( index*2<=size && comparator.compare(myHeapArr[(index*2)], myHeapArr[index]) == 1 )
            {
                if((index*2)+1<=size && comparator.compare(myHeapArr[(index*2)], myHeapArr[(index*2)+1]) == 1)
                {
                    swap((index*2) , index);
                    index = (index*2);
                }

                else
                {
                    swap((index*2)+1 , index);
                    index = (index*2)+1;
                }
                control2 = false;
            }

            if((index*2)+1<=size && comparator.compare(myHeapArr[(index*2)+1], myHeapArr[index]) == 1)
            {
                if((index*2)+1<=size && comparator.compare(myHeapArr[(index*2)], myHeapArr[(index*2)+1]) == 1 )
                {
                    swap((index*2) , index);
                    index = (index*2);
                }

                else
                {
                    swap((index*2)+1 , index);
                    index = (index*2)+1;
                }
                control2 = false;
            }

            if(control2 == true)
                control = false;
        }

        return removedElement;
    }

    public boolean offer(ColorClass item){

        if(size == capacity-1)
            allocate();

        myHeapArr[++size] = item;
        int tempSize = size;

        while ( tempSize > 1 && comparator.compare(myHeapArr[tempSize], myHeapArr[parent(tempSize)]) == 1 ) {
            swap(tempSize, parent(tempSize));
            tempSize = parent(tempSize);
        }

        for (int i= (size / 2); i >= 1; i--)
            sortMaxHeap(i);

        return true;
    }

    public void print()
    {

        for (int i = 1; i <= size; i++ )
        {
            System.out.print(myHeapArr[i]);
            System.out.println();
        }
    }

}
