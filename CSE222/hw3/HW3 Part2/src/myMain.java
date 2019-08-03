/**
 * @author Can Beyaznar 161044038
 */

import java.io.*;
import java.util.Stack;

public class myMain {

    public static void main(String[] args) throws Exception {

        System.out.println("test1.txt : ");
        File file1 = new File("src\\test1.txt");
        beginTest(file1);

        System.out.println("test2.txt : ");
        File file2 = new File("src\\test2.txt");
        beginTest(file2);

    }

    public static void beginTest(File file) throws Exception {
        BufferedReader myBuffer = new BufferedReader(new FileReader(file));

        String myStr;
        int rowCount=0;
        while ((myStr = myBuffer.readLine()) != null)
        {
            if(!myStr.isEmpty())
                rowCount++;
        }

        myStack<Element> myElements = new myStack<Element>();
        int numOfElements = rowCount - 1;

        myBuffer = new BufferedReader(new FileReader(file));
        for(int i=0; i<numOfElements; i++)
        {
            myStr = myBuffer.readLine();
            char myChar[] = new char[myStr.length()-4];

            for(int j=4; j<myStr.length(); j++)
                myChar[j-4] = myStr.charAt(j);
            String tempStr = new String(String.copyValueOf(myChar));
            myElements.push(new Element( myStr.charAt(0), Double.parseDouble(tempStr)  ));

        }
        System.out.println(myElements);
        myBuffer.readLine();
        myStr=myBuffer.readLine();
        System.out.println(myStr+"\n");

        myStack<Integer> UniqueFunctionIndex = new myStack<Integer>(); //it will keep the index of sin cos and abs...
        String PostFixStr = new String();
        PostFixStr = infixToPostfix(myStr, UniqueFunctionIndex);
        System.out.println("\nPostfix String:\n"+PostFixStr+"\n\n");

        double Result = calculateResult(PostFixStr, myElements, UniqueFunctionIndex);
        System.out.println("Result: "+ Result);
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-\n\n");
    }

    //In this method if we have negative number in string, there will not be any space until the number ends...
    public static String infixToPostfix(String myStr, myStack<Integer> UniqueFunctionIndex) throws Exception {
        String postFix = new String();
        myStack<Character> operandStack = new myStack<Character>();


        int current=0;

        while(current<myStr.length())
        {
            char myCharacter=myStr.charAt(current);

            if(myCharacter==' ')
            {
                //do nothing...
            }

            else if(myCharacter=='s' && myStr.charAt(current+1)=='i' && myStr.charAt(current+2)=='n')
            {
                operandStack.push('S');
                current=current+2;
            }


            else if(myCharacter=='c' && myStr.charAt(current+1)=='o' && myStr.charAt(current+2)=='s')
            {
                operandStack.push('C');
                current=current+2;
            }

            else if(myCharacter=='a' && myStr.charAt(current+1)=='b' && myStr.charAt(current+2)=='s')
            {
                operandStack.push('A');
                current=current+2;
            }

            else if(myCharacter=='(')
                operandStack.push(myCharacter);

            else if(Character.isLetterOrDigit(myCharacter) || myCharacter=='.')
            {
                postFix = postFix + myCharacter;
                if(Character.isLetter(myCharacter) || myStr.charAt(current+1) == ' ')
                    postFix = postFix + ' ';
            }


            else if(myCharacter==')')
            {
                while(!operandStack.empty() && operandStack.peek()!='(')
                {
                    char uniqueChar = operandStack.pop();
                    postFix = postFix + uniqueChar + " ";

                    if(uniqueChar == 'S' || uniqueChar == 'C' || uniqueChar == 'A'  )
                    {
                        UniqueFunctionIndex.push(postFix.length()-2);
                    }
                }


                if(operandStack.peek() == '(')
                    operandStack.pop();

            }

            else
            {
                if( (myCharacter=='-' || myCharacter=='+') && (Character.isLetterOrDigit(myStr.charAt(current+1)) || myStr.charAt(current+1)=='('  ) )
                    postFix = postFix + "0 ";


                while(!operandStack.empty()
                        && (priority(operandStack.peek()) >= priority(myCharacter)) )
                {
                    char uniqueChar = operandStack.pop();
                    postFix = postFix + uniqueChar + " ";

                    if(uniqueChar == 'S' || uniqueChar == 'C' || uniqueChar == 'A'  )
                    {
                        UniqueFunctionIndex.push(postFix.length()-2);
                    }

                }
                operandStack.push(myCharacter);

            }
                current++;
        }
        while(!operandStack.empty())
        {
            if(priority(operandStack.peek()) != 0 )
            {
                if(operandStack.peek() == 'S' || operandStack.peek() == 'C' || operandStack.peek() == 'A' )
                    UniqueFunctionIndex.push(postFix.length());

                postFix = postFix + operandStack.pop() + " ";
            }

            else
                operandStack.pop();
        }

        return postFix;
    }

    public static double calculateResult(String postFixStr, myStack<Element> myElements , myStack<Integer> UniqueFunctionIndex ) throws Exception {
        double Result=0.0;
        myStack<Double> variableStack = new myStack<Double>();

        int current=0;
        while(current < postFixStr.length())
        {
            char myCharacter = postFixStr.charAt(current);

            if (myCharacter == ' ')
            {
                //do nothing...
            }

            else if(Character.isLetter(myCharacter))
            {
                myStack<Element> tempElementStack = new myStack<Element>(myElements);
                boolean control = true;

                while(!tempElementStack.empty() && control)
                {
                    Element tempElem =new Element(tempElementStack.pop());
                    if(myCharacter==tempElem.getVariableName())
                    {
                        variableStack.push(tempElem.getValue());
                        control=false;
                    }
                }
                if(control) // if the while did not enter the statement
                {
                    double firstElement = variableStack.pop();
                    myMathClass myMath = new myMathClass();

                    myStack<Integer> tempUnique = new myStack<Integer>(UniqueFunctionIndex);
                    boolean controlUnique=true;

                    while(!tempUnique.empty() && controlUnique)
                    {

                        int tempIndex = tempUnique.pop();
                        if(tempIndex == current)
                            controlUnique = false;
                    }

                    if(controlUnique==false)
                    {
                        if(myCharacter == 'S')
                            variableStack.push( myMath.sin(firstElement) );

                        else if(myCharacter == 'C')
                            variableStack.push( myMath.cos(firstElement) );

                        else if(myCharacter == 'A')
                            variableStack.push( myMath.abs(firstElement) );


                        else
                        {
                            System.out.println("UNKNOWN CHARECTER");
                            return 0.0;
                        }

                    }

                }

            }

            else if(Character.isDigit(myCharacter))
            {
                String doubleNum=new String();
                int i=0;
                while( (Character.isDigit(postFixStr.charAt(current+i))) || (postFixStr.charAt(current+i) == '.') )
                {
                    doubleNum = doubleNum + postFixStr.charAt(current+i);
                    i++;
                }
                variableStack.push(Double.parseDouble(doubleNum));
                current=current+i-1;
            }

            else {
                double firstElement = variableStack.pop();
                double secondElement = variableStack.pop();

                if (myCharacter == '+')
                    variableStack.push(firstElement + secondElement);

                else if (myCharacter == '-')
                    variableStack.push(secondElement - firstElement);

                else if (myCharacter == '*')
                    variableStack.push(firstElement * secondElement);

                else if (myCharacter == '/')
                    variableStack.push(secondElement + firstElement);
            }
            current++;
        }
        if(!variableStack.empty())
            return variableStack.pop();

        else
        {
            System.out.println("Something Went Wrong!!");
            return 0.0;
        }

    }

    public static int priority(char Input)
    {
        if(Input=='+' || Input=='-')
            return 1;
        else if(Input=='*' || Input=='/')
            return 2;
        else if(Input=='^')
            return 3;

        else if(Input=='S' || Input=='C' || Input=='A') //sin(), cos(), abs() has highest priority!!
            return 4;

        else
            return 0;

    }

}
