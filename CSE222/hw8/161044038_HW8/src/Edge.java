/**
 * @author Can BEYAZNAR 161044038
 */

public class Edge{
    private int src;
    private int dest;

    public Edge(){
        //UNKNOWN
        src = Integer.MAX_VALUE;
        dest = Integer.MAX_VALUE;
    }

    public Edge(int src_Input, int dest_Input){
        src = src_Input;
        dest = dest_Input;
    }

    public int getDest() {
        return dest;
    }

    public int getSrc() {
        return src;
    }

    @Override
    public boolean equals(Object obj) {

        return ( ((Edge) obj).src == src
                    && ((Edge) obj).dest == dest );
    }

    @Override
    public String toString() {
        return ("Src : " + src + " Dest : " + dest);
    }
}