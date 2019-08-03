/**
 * @author Can BEYAZNAR - 161044038
 *
 */
import java.util.*;

public class Word_Map implements Map, Iterable, Iterator
{

    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[];
    private Node head = new Node();
    private int size = 0;

    public Word_Map() {
        this.table = new Node[INITCAP];
    }

    @Override
    public Iterator iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return (head.prev != null);
    }

    @Override
    public Object next() {

        if(hasNext())
        {
            String temp = head.key;
            head = head.prev;
            return temp;
        }
        return null;
    }

    // complete this class according to the given structure in homework definition
    static class Node {
        private String key;
        private File_Map value;
        private Node next;
        private Node prev;

        public Node(){
            key = null;
            value = null;
            next = null;
            prev = null;
        }

        public Node(String Input_Key, File_Map Input_Value){
            key = new String(Input_Key);
            value = Input_Value;
            next = null;
            prev = null;
        }

        public File_Map getValue() {
            return value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(File_Map value) {
            this.value = value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() { return (size()==0); }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key) {

        int index = find(key);

        if ( table[index] != null && table[index].key.equals(key) )
            return true;

        return false;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) {

        for (int index=0; index<table.length; index++)
        {
            if (table[index] != null && table[index].value.equals(value))
                return true;
        }

        return false;
    }

    @Override
    public Object get(Object key) {

        int index = find(key);

        if ( table[index] != null )
            return table[index].value;

        return null;
    }

    private int find(Object key) {
        int index = key.hashCode() % table.length;
        if(index < 0)
            index += table.length;
        while ( (table[index] != null) && (!key.equals(table[index].key)) ){
            index++;
            if(index >= table.length)
                index = 0;
        }
        return index;
    }

    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value) {

        int index = find(key);
        if (table[index] == null){

            table[index] = new Node((String) key,(File_Map) value);
            if(isEmpty())
                head = table[index];

            else
            {
                Node temp = head;
                head.next = table[index];
                head = head.next;
                head.prev = temp;
            }

            CURRCAP--;
            size++;
            double LOAD_FACTOR  = ((double)size()) / (double) (table.length);
            if (LOAD_FACTOR > LOADFACT)
                rehash();
            return null;
        }
        size++;
        Object oldValue = table[index].value;
        table[index].value = (File_Map) value;
        return oldValue;
    }

    public void rehash(){

        Node[] oldTable = new Node[table.length];
        for(int i=0; i<oldTable.length; i++)
            oldTable[i] = table[i];

        table = new Node[2* table.length + 1 ];

        CURRCAP = table.length;
        size=0;
        head = new Node();
        for (int i = 0; i<oldTable.length; i++ )
        {
            if( (oldTable[i] != null) )
                put(oldTable[i].key, oldTable[i].value);
        }
    }

    @Override
    /*You do not need to implement remove function
    * */
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {
        TreeSet myKeys = new TreeSet(m.keySet());
        Collection myValues = new ArrayList(m.values());
        Object keys[] = myKeys.toArray();
        for(int i=0; i<myKeys.size(); i++)
            put(keys[i], ((ArrayList) myValues).get(i));

    }

    @Override
    public void clear() {
        table = new Node[table.length];
        head = new Node();
        CURRCAP = INITCAP;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {
        Set<String> mySet = new TreeSet<>();
        Node temp = head;
        while (temp != null)
        {
            mySet.add(temp.getKey());
            temp = temp.prev;
        }
        return mySet;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {
        Collection<File_Map> myValues = new ArrayList<File_Map>();
        Node temp = head;
        while (temp != null)
        {
            myValues.add(temp.value);
            temp = temp.prev;
        }

        return myValues;
    }

    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        return null;
    }


}
