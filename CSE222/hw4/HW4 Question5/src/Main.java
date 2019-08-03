public class Main {

    public static void main(String args[]) {
        TestFunction();
    }

    public static void TestFunction()
    {
        {
            System.out.println("-TEST1-");
            int arr[][] = new int[4][4];
            arr[0] = new int[]{1, 2, 3, 4};
            arr[1] = new int[]{5, 6, 7, 8};
            arr[2] = new int[]{9, 10, 11, 12};
            arr[3] = new int[]{13, 14, 15, 16};

            System.out.println("Your 2D Array: ");
            for(int i=0; i<arr.length; i++)
            {
                for(int j=0; j<arr[i].length; j++)
                    System.out.print(arr[i][j]+" ");
                System.out.println();
            }
            System.out.println();
            System.out.println();

            myElements test = new myElements(arr);
            System.out.println("---------------------------");
            System.out.println("The result is : \n");

            test.parseToIter();
            test.printWithIterator();
            System.out.println("---------------------------");
        }

        {
            System.out.println("-TEST2-");
            int arr[][] = new int[4][6];
            arr[0] = new int[]{1, 2, 3, 4,17,18};
            arr[1] = new int[]{5, 6, 7, 8,19,20};
            arr[2] = new int[]{9, 10, 11, 12,21,22};
            arr[3] = new int[]{13, 14, 15, 16,23,24};

            System.out.println("Your 2D Array: ");
            for(int i=0; i<arr.length; i++)
            {
                for(int j=0; j<arr[i].length; j++)
                    System.out.print(arr[i][j]+" ");
                System.out.println();
            }
            System.out.println();
            System.out.println();

            myElements test = new myElements(arr);
            System.out.println("---------------------------");
            System.out.println("The result is : \n");

            test.parseToIter();
            test.printWithIterator();
            System.out.println("---------------------------");
        }

        {
            System.out.println("-TEST3-");
            int arr[][] = new int[4][1];
            arr[0] = new int[]{1};
            arr[1] = new int[]{5};
            arr[2] = new int[]{9};
            arr[3] = new int[]{13};

            System.out.println("Your 2D Array: ");
            for(int i=0; i<arr.length; i++)
            {
                for(int j=0; j<arr[i].length; j++)
                    System.out.print(arr[i][j]+" ");
                System.out.println();
            }
            System.out.println();
            System.out.println();

            myElements test = new myElements(arr);
            System.out.println("---------------------------");
            System.out.println("The result is : \n");

            test.parseToIter();
            test.printWithIterator();
            System.out.println("---------------------------");
        }
    }

}
