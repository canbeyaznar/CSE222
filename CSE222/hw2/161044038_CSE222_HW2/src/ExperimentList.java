/**
 * @author CAN BEYAZNAR 161044038
 *
 */

import java.lang.Iterable;
import java.util.Iterator;

/**
 *
 */
public class ExperimentList implements Iterable<ExperimentList>, Iterator<ExperimentList>{

    private myNode head;
    private myNode iter=null;

    private static class myNode{
        private Experiment dataNode;
        private myNode nextNode;
        private myNode nextDay;

        myNode(Experiment Input) { dataNode=Input; nextNode=null; nextDay=null;}

        myNode(myNode temp) {
            if(temp == null)
            {
                dataNode=null;
                nextNode=null;
                nextDay=null;
            }
            else
            {
                dataNode=temp.dataNode;
                nextNode=temp.nextNode;
                nextDay=temp.nextDay;
            }
        }
        public Experiment getDataNode(){return dataNode; }

        public String toString(){
            return dataNode.toString();
        }
    }

    /**
     *
     * @return ExperimentList iterator
     */
    @Override
    public Iterator<ExperimentList> iterator() {

        iter = head;
        return this;
    }

    @Override
    public ExperimentList next() {

        if(hasNext() == true )
            iter = iter.nextNode;

        return this;
    }

    @Override
    public boolean hasNext() {

        if(iter.nextNode != null)
            return true;
        else
            return false;
    }

    public Experiment getIterator()
    {
        return iter.dataNode;
    }

    /**
     *
     * @param Exp_Input Experiment input for adding
     */
    public void addExp(Experiment Exp_Input)
    {
        if(head==null)
        {
            head = new myNode(Exp_Input);
        }

        else
        {
            if(head.dataNode.getDay() < Exp_Input.getDay())
            {
                myNode temp = head;
                myNode tempExp = new myNode(Exp_Input);
                if(temp.nextDay != null)
                {
                    while(temp.nextDay != null && temp.nextDay.dataNode.getDay() < Exp_Input.getDay())
                        temp=temp.nextDay;

                    if(temp.nextDay == null) // adding end of the list
                    {
                        tempExp.nextDay = temp.nextDay;
                        temp.nextDay = tempExp;

                        while(temp.nextNode != null )
                            temp=temp.nextNode;
                        tempExp.nextNode=temp.nextNode;
                        temp.nextNode=tempExp;

                    }

                    else
                    {
                        if(temp.nextDay.dataNode.getDay() == Exp_Input.getDay())
                        {
                            temp = temp.nextDay;
                            while( temp.nextNode!=null && temp.nextNode.dataNode.getDay() == Exp_Input.getDay())
                                temp=temp.nextNode;
                            tempExp.nextNode = temp.nextNode;
                            temp.nextNode = tempExp;
                            return;
                        }

                        else if(temp.nextDay.dataNode.getDay() > Exp_Input.getDay())
                        {
                            tempExp.nextDay = temp.nextDay;
                            temp.nextDay = tempExp;
                            while(temp.nextNode != null && temp.nextNode.dataNode.getDay() != tempExp.nextDay.dataNode.getDay())
                                temp=temp.nextNode;
                            tempExp.nextNode=temp.nextNode;
                            temp.nextNode=tempExp;
                            return;
                        }
                    }


                }

                else
                {
                    tempExp.nextDay = temp.nextDay;
                    temp.nextDay=tempExp;

                    while(temp.nextNode != null)
                        temp = temp.nextNode;
                    tempExp.nextNode = temp.nextNode;
                    temp.nextNode = tempExp;

                }
                /*
                myNode temp = head;

                if(temp.nextDay!=null)
                {
                    if(temp.nextDay.dataNode.getDay()>=Exp_Input.getDay())
                    {
                        myNode tempExp = new myNode(Exp_Input);
                        if(temp.nextDay.dataNode.getDay()>Exp_Input.getDay())
                        {
                            tempExp.nextDay=temp.nextDay;
                            temp.nextDay=tempExp;
                        }

                        while(temp.nextNode!=null && temp.nextNode.dataNode.getDay()<tempExp.dataNode.getDay() )
                        {
                            temp=temp.nextNode;
                        }
                        tempExp.nextNode=temp.nextNode;
                        temp.nextNode=tempExp;
                        return;
                    }


                }

                while(temp.nextDay != null && temp.dataNode.getDay() < Exp_Input.getDay() )
                {
                    temp=temp.nextDay;
                }

                if(temp.nextDay==null)
                {
                    myNode tempExp = new myNode(Exp_Input);
                    System.out.println("AA:"+Exp_Input);
                    if(temp.dataNode.getDay() < Exp_Input.getDay())
                        temp.nextDay = tempExp;

                    while(temp.nextNode!=null )
                        temp=temp.nextNode;

                    temp.nextNode = tempExp;
                }

                else if(temp.dataNode.getDay() == Exp_Input.getDay())
                {
                    myNode tempExp = new myNode(Exp_Input);
                    while(temp.nextNode.dataNode.getDay() != Exp_Input.getDay())
                        temp=temp.nextNode;
                    tempExp.nextNode=temp.nextNode;
                    temp.nextNode=tempExp;

                }
*/
            }

            else if(head.dataNode.getDay() == Exp_Input.getDay() )
            {

                myNode temp = head;
                while(temp.nextNode != null && temp.nextNode.dataNode.getDay() == Exp_Input.getDay() )
                    temp = temp.nextNode;

                myNode temp2 = new myNode(Exp_Input);
                temp2.nextNode = temp.nextNode;
                temp.nextNode = temp2;

            }

            else if(head.dataNode.getDay() > Exp_Input.getDay() )
            {
                myNode temp = new myNode(Exp_Input);

                temp.nextNode = head;
                temp.nextDay = head;
                head = temp;

            }

        }
    }


    public myNode getNode(int day, int index)
    {
        myNode temp = head;
        while(temp!=null && temp.dataNode.getDay() < day)
        {
            temp = temp.nextDay;
        }

        int count=0;
        while(temp!=null && temp.dataNode.getDay() == day)
        {
            if(count==index)
                return temp;
            count++;
            temp=temp.nextNode;
        }
        return null;

    }

    public Experiment getExp(int day, int index)
    {
        myNode temp = head;
        while(temp!=null && temp.dataNode.getDay() < day)
        {
            temp = temp.nextDay;
        }

        int count=0;
        while(temp!=null && temp.dataNode.getDay() == day)
        {
            if(count==index)
                return temp.dataNode;
            count++;
            temp=temp.nextNode;
        }
        return null;

    }


    public void setExp(int day, int index, Experiment expInput)
    {
        if(day==expInput.getDay())
        {
            myNode temp = getNode(day,index);
            temp.dataNode = new Experiment(expInput) ;
        }
        else
            System.out.println("Your day of experiment input is not same with day input!! (Set incompleted) ");

    }

    public void removeExp(int day, int index)
    {

        if(getNode(day,index)==null){
            System.out.println("Your day or index could not found in this ExperimentList!!");
            return;
        }
        myNode temp = head;
        myNode FirstDayofExp = head; //it will keep the previous day node of current node

        while(temp.nextDay != null && temp.dataNode.getDay() != day)
        {
            if(FirstDayofExp.dataNode.getDay() != temp.dataNode.getDay())
                FirstDayofExp=temp;
            temp=temp.nextDay;
        }

        if(index == 0 && temp.nextDay==null && temp.nextNode==null)
        {
            FirstDayofExp.nextDay=null;
            while(FirstDayofExp.nextNode!=null && FirstDayofExp.nextNode.dataNode.getDay() != day )
                FirstDayofExp=FirstDayofExp.nextNode;


            FirstDayofExp.nextNode=null;
            temp=null;
            return;
        }

        if(index==0)
        {

            if(temp.nextNode == null )
            {
                temp=null;
                return;
            }

            if(temp.dataNode.getDay() != temp.nextNode.dataNode.getDay() )
            {
                FirstDayofExp.nextDay = temp.nextDay;
            }

            else    //there is only one experiment on this day
            {
                FirstDayofExp.nextDay = temp.nextNode;
                temp.nextNode.nextDay = temp.nextDay;
            }
            myNode temp2 = FirstDayofExp;
            while( temp2.nextNode!=null && temp2.nextNode.dataNode.getDay() != day )
                temp2=temp2.nextNode;
            temp2.nextNode = temp.nextNode;
        }

        else
        {
            int count=0;
            while(count!=index )
            {
                if(count==index-1)
                {
                    temp.nextNode = temp.nextNode.nextNode;
                    return;
                }
                temp=temp.nextNode;
                count++;
            }

            temp.nextNode = temp.nextNode.nextNode;
        }


    }

    public ExperimentList listExp(int day)
    {
        ExperimentList Result = new ExperimentList();

        myNode temp = head;
        int control=1;

        while(temp != null && control!=0)
        {

            if(temp.dataNode.getDay() == day)
                control=0;

            else
                temp = temp.nextDay;

        }
        if(control == 0)
        {
            while( temp !=null && temp.dataNode.getDay() == day)
            {
                if(temp.dataNode.getCompleted() == true)
                    Result.addExp(temp.dataNode);
                temp = temp.nextNode;
            }
            return Result;
        }
        return null;

    }

    public void removeDay(int day)
    {

        myNode temp = head;
        int control=1;

        while(temp!=null && control!=0)
        {
            if(temp.dataNode.getDay() == day)
                control=0;
            else
                temp=temp.nextDay;
        }

        if(control==0)
        {
            while( temp!=null && temp.dataNode.getDay() == day)
            {
                removeExp(day,0);
                temp=getNode(day,0);
            }
        }

        else
            System.out.println("Could not found in this day in your ExperimentList... ");
    }

    public void orderDay(int day)
    {
        ExperimentList Result = new ExperimentList();
        myNode temp = head;
        int control=1;

        while(temp != null && control!=0)
        {
            if(temp.dataNode.getDay() == day)
                control=0;

            else
                temp = temp.nextDay;
        }
        if(control == 0)
        {
            myNode temp2 = new myNode(temp) ;
            int count=0;

            while( temp !=null && temp.dataNode.getDay() == day)
            {
                count++;
                temp = temp.nextNode;
            }

            for(int i=0; i<count; i++)
            {
                for(int j=0; j<count; j++)
                {
                    if (getExp(day,i).getAccuracy() < getExp(day, j).getAccuracy())
                    {
                        Experiment myExp = getExp(day,i);
                        setExp(day,i,getExp(day,j));
                        setExp(day,j,myExp);
                    }
                }
            }
        }

    }


    public void orderExperiments()
    {
        myNode tempExp = head;
        myNode headExp = head;

        int size=0;
        while(tempExp.nextNode != null)
        {
            size++;
            tempExp=tempExp.nextNode;
        }


        Experiment[] Result = new Experiment[size];
        tempExp=headExp;

        int i=0;
        while(tempExp.nextNode !=null)
        {
            Result[i] = new Experiment(tempExp.dataNode);
            i++;
            tempExp=tempExp.nextNode;
        }

        for(i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                if(Result[i].getAccuracy() < Result[j].getAccuracy())
                {
                    Experiment temp = Result[i];
                    Result[i] = Result[j];
                    Result[j] = temp;
                }
            }
        }

        myNode sortedResult = new myNode(Result[0]);
        for(i=1; i<size; i++)
        {
            myNode temp = sortedResult;
            while (temp.nextNode != null)
                temp=temp.nextNode;
            temp.nextNode = new myNode(Result[i]);
        }

        ExperimentList sortedExp = new ExperimentList();
        sortedExp.head = sortedResult;

        System.out.println(sortedExp);



    }

    public myNode getHead(){ return head; }

    public void listAll()
    {
        System.out.println("List experiment view:");
        myNode last = head;
        while( last != null) {
            System.out.println(last.dataNode.toString());
            last = last.nextNode;
        }
        System.out.println("List day view:");
        last = head;
        while( last != null) {
            System.out.println(last.dataNode.toString());
            last = last.nextDay;
        }
    }


    public String toString()
    {
        String PrintIt = "ExperimentList:\n";
        myNode temp = head;

        while(temp!=null)
        {
            PrintIt = PrintIt + temp.dataNode.toString();
            temp=temp.nextNode;
        }

        PrintIt = PrintIt + "\nDay nodes:\n";

        temp=head;
        while(temp!=null)
        {
            PrintIt = PrintIt + temp.dataNode.toString();
            temp=temp.nextDay;
        }
        return PrintIt;

    }

    public void print()
    {


        System.out.println("ExperimentList : ");
        myNode temp = new myNode(head);

        while(temp!=null)
        {
            System.out.println(temp);
            temp=temp.nextNode;
        }

        myNode temp2 = new myNode(head);

        System.out.println("-o-o-o-o-o-o-o-Day nodes-o-o-o-o-o-o-o-");

        while(temp2!=null)
        {
            System.out.println(temp2);
            temp2=temp2.nextDay;
        }
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-");

    }


}
