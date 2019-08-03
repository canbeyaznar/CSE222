/**
 * @author Can BEYAZNAR - 161044038
 */


public class ElementNeighbour {

    //private myStack<ElementNeighbour> characterStack;
    private char data;
    private int loc_X;
    private int loc_Y;
    private boolean used;

    public ElementNeighbour()
    {
        data='0';
        loc_X=-1;
        loc_Y=-1;
        used=false;
    }
    public ElementNeighbour(char data_Input, int X, int Y)
    {
        data=data_Input;
        loc_X=X;
        loc_Y=Y;
    }

    public ElementNeighbour(char data_Input, int X, int Y, boolean isUsed){
        data=data_Input;
        loc_X=X;
        loc_Y=Y;
        used = isUsed;
    }

    public ElementNeighbour(ElementNeighbour Input)
    {
        data=Input.data;
        loc_X=Input.loc_X;
        loc_Y=Input.loc_Y;
        used = Input.used;
    }

    public void setUsed(boolean temp){used=temp;}
    public void setData(char data) {this.data = data; }
    public void setLoc_Y(int loc_Y) { this.loc_Y = loc_Y;  }
    public void setLoc_X(int loc_X) { this.loc_X = loc_X;  }

    public int getLoc_X(){return loc_X; }
    public int getLoc_Y(){  return loc_Y; }
    public char getData() { return data; }
    public boolean getUsed() {return used; }

    public String toString()
    {

        return new String("Data: "+data+" loc_X: "+loc_X+" loc_Y: "+loc_Y+" used: "+used+"\n" );
    }

}
