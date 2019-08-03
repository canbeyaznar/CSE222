/**
 * @author Can Beyaznar 161044038
 */

public class Element {

    private char variableName;
    private double value;

    public Element()
    {
        variableName='0';
        value=0.0;
    }

    public Element(char name, double val)
    {
        variableName = name;
        value=val;
    }

    public Element(Element Input)
    {
        variableName=Input.variableName;
        value=Input.value;
    }

    public double getValue() { return value;  }
    public char getVariableName() { return variableName;  }

    public void setValue(double value) { this.value = value; }
    public void setVariableName(char variableName) { this.variableName = variableName;   }

    public String toString()
    {
        return new String(variableName+" = "+value+"\n");
    }

}
