/**
 * @author CAN BEYAZNAR 161044038
 *
 */

import java.sql.SQLOutput;
import java.sql.Time;
import java.util.Iterator;
import java.util.SortedMap;

public class myDriverClass {

    public void Test()
    {

        Experiment Exp1 = (new Experiment("Experiment1",1,new Time(1,2,3),false,0));
        Experiment Exp2 = (new Experiment("Experiment2",1,new Time(1,40,3),true,7));
        Experiment Exp3 = (new Experiment("Experiment3",1,new Time(7,50,3),true,5));
        Experiment Exp4 = (new Experiment("Experiment2",3,new Time(3,12,3),true,9));
        Experiment Exp5 = (new Experiment("Experiment3",3,new Time(2,15,3),true,12));
        Experiment Exp6 = (new Experiment("Experiment5",3,new Time(1,21,3),true,11));
        Experiment Exp7 = (new Experiment("Experiment6",2,new Time(1,31,3),true,13));
        Experiment Exp8 = (new Experiment("Experiment2",2,new Time(5,1,3),false,0));
        Experiment Exp9 = (new Experiment("Experiment3",5,new Time(6,1,3),true,11));
        Experiment Exp10 = (new Experiment("Experiment4",5,new Time(16,1,3),false,0));


        ExperimentList TestList = new ExperimentList();

        TestList.addExp(Exp1);
        TestList.addExp(Exp2);
        TestList.addExp(Exp3);
        TestList.addExp(Exp4);
        TestList.addExp(Exp5);
        TestList.addExp(Exp6);
        TestList.addExp(Exp7);
        TestList.addExp(Exp8);
        TestList.addExp(Exp9);
        TestList.addExp(Exp10);

        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-Our Test List-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-");
        System.out.println(TestList);
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-");

        System.out.println("                            getExp Method Test\n");
        System.out.println("Day 1 Index 1 --> "+TestList.getExp(1,1));
        System.out.println("Day 2 Index 2 --> "+TestList.getExp(2,2)+" (Day 2 has 2 Experiment...)");
        System.out.println("Day 3 Index 0 --> "+TestList.getExp(3,0));
        System.out.println("Day 5 Index 1 --> "+TestList.getExp(5,1));
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-");

        System.out.println("                            setExp Method Test\n");
        System.out.println("Day 1 Index 1 before the setExp --> "+TestList.getExp(1,1)  );
        TestList.setExp(1,1,new Experiment("Experiment3",1,new Time(7,50,3),true,5));
        System.out.println("Day 1 Index 1 after the setExp --> "+TestList.getExp(1,1));
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-");

        System.out.println("                            listExp Method Test\n");
        System.out.println("listExp method for Day 3");
        ExperimentList tempExpList = new ExperimentList();
        tempExpList = TestList.listExp(3);
        System.out.println("Listed ExperimentList Class: \n"+tempExpList);
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-");

        System.out.println("                            removeDay Method Test\n");
        System.out.println("-Before the removeDay method (Removing Day 3)-\n"+TestList);
        TestList.removeDay(3);
        System.out.println("-After the removeDay method-\n"+TestList);
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-");


        ExperimentList SecondTestList = new ExperimentList();
        SecondTestList.addExp(Exp1);
        SecondTestList.addExp(Exp2);
        SecondTestList.addExp(Exp3);
        SecondTestList.addExp(Exp7);
        SecondTestList.addExp(Exp8);
        SecondTestList.addExp(Exp4);
        SecondTestList.addExp(Exp5);
        SecondTestList.addExp(Exp6);
        SecondTestList.addExp(Exp9);
        SecondTestList.addExp(Exp10);

        System.out.println("                            orderDay Method Test\n");
        System.out.println("Before the orderDay method: \n"+SecondTestList);
        SecondTestList.orderDay(1);
        SecondTestList.orderDay(3);
        System.out.println("After the orderDay method for day 1 and day 3 : \n"+SecondTestList);
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-");

        System.out.println("                            orderExperiments Method Test\n");
        SecondTestList.orderExperiments();
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-");

        System.out.println("                            ExperimentList Iterable Test\n");
        Iterator<ExperimentList> myIter = SecondTestList.iterator();
        while(myIter.hasNext())
            System.out.println(myIter.next().getIterator());
        System.out.println("-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-");

        SecondTestList.listAll();

    }

}
