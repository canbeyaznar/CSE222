/**
 * @author Can BEYAZNAR 161044038
 */

import java.util.Iterator;

public abstract class GraphADT {

    public abstract void insert(Edge edge);
    public abstract boolean isEdge(int source, int dest);
    public abstract Edge getEdge(int source, int dest);

    public abstract Iterator<Edge> edgeIterator(int source);

}
