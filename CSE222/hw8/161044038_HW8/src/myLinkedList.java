/**
 * @author Can BEYAZNAR 161044038
 */

import java.util.Iterator;

public class myLinkedList<E> implements Iterable<E> {

    private myNode<E> head;

    private static class myNode<E>{

        E data;
        myNode next;

        public myNode(){
            data = null;
            next = null;
        }

        public myNode(E val){
            data = val;
            next = null;
        }

    }

    private class Iter implements Iterator<E>{

        myNode<E> myIter = head;

        @Override
        public boolean hasNext() {
            return (myIter != null);
        }

        @Override
        public E next() {
            if(hasNext()){
                E temp = myIter.data;
                myIter = myIter.next;
                return temp;
            }
            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return (new Iter());
    }

    public myLinkedList(){
        head = null;
    }

    public boolean isEmpty(){
        return (head==null);
    }

    public void insert(E val){

        if(head == null)
            head = new myNode<E>(val);
        else{
            myNode<E> temp = new myNode<E>(val);
            temp.next = head;
            head = temp;
        }
    }

    public E poll(){
        E temp = head.data;
        head = head.next;

        return temp;
    }

    public boolean contains(E num){
        myNode<E> temp = head;
        while (temp != null){
            if(temp.data.equals(num))
                return true;
            temp = temp.next;
        }
        return false;
    }

    public void print(){

        myNode<E> temp = head;
        while(temp != null)
        {
            System.out.print( temp.data + " " );
            temp = temp.next;
        }
        System.out.println();
    }

    public myNode<E> getHead() {
        return head;
    }

    @Override
    public String toString(){
        String result = new String();
        myNode temp = head;
        while (temp.next != null){
            result += temp.data + ", ";
            temp = temp.next;
        }
        result += temp.data;
        return result;
    }

}
