/**
 * @author Can BEYAZNAR 161044038
 */

import java.util.Iterator;

public class ListGraph extends GraphADT {

    private myLinkedList<Edge> Edges[];
    private boolean isDirected = false;

    public ListGraph(int numVertex, boolean directed){

        isDirected = directed;
        Edges = new myLinkedList[numVertex];
        for(int i=0; i<Edges.length; i++)
            Edges[i] = new myLinkedList<>();
    }

    @Override
    public void insert(Edge edge) {
        if(Edges.length<edge.getSrc()){
            System.out.println("VERTEX < NUMBER_OF_PEOPLE");
            return;
        }

        Edges[edge.getSrc()-1].insert(edge);
        if(!isDirected)
            Edges[edge.getDest()-1].insert(new Edge(edge.getDest(), edge.getSrc()));
    }

    @Override
    public boolean isEdge(int source, int dest) {
        return Edges[source-1].contains(new Edge(source,dest));
    }

    @Override
    public Edge getEdge(int source, int dest) {

        if (!Edges[source-1].isEmpty()){
            Iterator<Edge> iter = Edges[source-1].iterator();
            while (iter.hasNext()){
                Edge temp = iter.next();
                if (temp.equals(new Edge(source,dest)))
                    return temp;
            }
        }
        return null;
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {

        if(source > Edges.length )
            return null;

        if(!Edges[source-1].isEmpty())
            return Edges[source-1].iterator();
        return null;
    }

    public void print(){
        for(int i=0; i<Edges.length; i++){
            if(!Edges[i].isEmpty())
                System.out.println("Vertex "+ (i+1) +"'s elements are: " + Edges[i]);
        }
    }

    @Override
    public String toString() {

        String result = new String();

        for(int i=0; i<Edges.length; i++){
            if(!Edges[i].isEmpty())
                result += "Vertex "+ (i+1) +"'s elements are: " + Edges[i] + "\n";
        }
        return result;
    }
}