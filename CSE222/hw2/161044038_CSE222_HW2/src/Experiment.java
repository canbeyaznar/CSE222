/**
 * @author CAN BEYAZNAR 161044038
 *
 */

import java.sql.Time;

public class Experiment {

    private String setup;
    private Time time;
    private int day;
    private boolean completed;
    private float accuracy;

    /**
     * Default Constructor
     */
    public Experiment()
    {
        setup=null;
        day=0;
        time=null;
        completed=false;
        accuracy=0;
    }

    /**
     *
     * @param setupInput    setup information
     * @param dayInput      day information
     * @param timeInput     time information
     * @param completedInput        completed information
     * @param accuracyInput         accuracy information
     */
    public Experiment(String setupInput, int dayInput, Time timeInput,
                      boolean completedInput, float accuracyInput)
    {
        setup = setupInput;
        day = dayInput;
        time = timeInput;
        completed = completedInput;
        accuracy = accuracyInput;
    }

    /**
     *
     * @param ExpInput experiment input fr copy constructor
     */
    public Experiment(Experiment ExpInput)
    {
        setup = ExpInput.getSetup();
        day = ExpInput.getDay();
        time = ExpInput.getTime();
        completed = ExpInput.getCompleted();
        accuracy = ExpInput.getAccuracy();
    }

    public String getSetup(){ return setup;}
    public Time getTime() { return time;}
    public int getDay() {return day; }
    public boolean getCompleted() {return completed; }
    public float getAccuracy() { return accuracy;  }

    public String toString()
    {
        return "setup : "+setup+", "+"day : "+day+", "+"time : "+time+
                ", "+"completed : "+completed+", "+"accuracy : "+accuracy+"\n";
    }

    public void print(){

        System.out.println("setup : "+setup+", " );
        System.out.println("day : "+day+"," );
        System.out.println("time : "+time+"," );
        System.out.println("completed : "+completed+"," );
        System.out.println("accuracy : "+accuracy );
    }

}
