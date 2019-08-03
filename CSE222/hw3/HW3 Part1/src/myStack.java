/**
 * @author Can BEYAZNAR - 161044038
 */


public class myStack<T> {

    private myNode<T> head;

    private static class myNode<T>{
        T data;
        private myNode next;

        myNode(T Input)
        {
            data = Input;
            next = null;
        }

        myNode(myNode<T> Input)
        {
            data = Input.data;
            next = Input.next;
        }

    }

    public myStack()
    {
        head = null;
    }

    public myStack(myStack<T> Input)
    {
        head = Input.head;
    }

    public T push(T Input)
    {
        if(head == null)
            head = new myNode<T>(Input);

        else
        {
            myNode<T> myInput = new myNode<T>(Input);
            myNode<T> temp = head;
            while(temp.next != null)
                temp = temp.next;

            temp.next = myInput;
        }
        return Input;
    }

    public T pop()
    {
        if(head.next == null)
        {
            T temp = head.data;
            head=null;
            return temp;
        }

        myNode<T> nodeTemp = head;
        while(nodeTemp.next.next != null)
            nodeTemp = nodeTemp.next;

        T temp = (T) nodeTemp.next.data;
        nodeTemp.next = null;
        return temp;

    }

    public T peek()
    {
        myNode<T> temp = head;
        while(temp.next!=null)
            temp=temp.next;
        return temp.data;
    }

    public boolean empty(){
        return (head==null);
    }

    public String toString()
    {
        if(head==null)
            return null;

        String myString = new String();
        myNode<T> temp = head;
        while(temp!=null)
        {
            myString += temp.data + " ";
            temp=temp.next;
        }

        return myString;

    }

}
