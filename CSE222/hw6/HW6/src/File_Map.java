/**
 * @author Can BEYAZNAR - 161044038
 *
 */

import java.util.*;

public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
    ArrayList<String> fnames = new ArrayList<String>();
    ArrayList<List<Integer>> occurances = new ArrayList<List<Integer>>();

    @Override
    public int size() {
        return fnames.size();
    }

    @Override
    public boolean isEmpty() {
        if (fnames.size() == 0)
            return false;
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        return fnames.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return occurances.contains(value);
    }

    @Override
    public Object get(Object key) {

        if(fnames.contains(key))
            return occurances.get(fnames.indexOf(key));
        return null;
    }

    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {
        if(fnames.contains(key))
        {
            int index = fnames.indexOf(key);
            Object oldValue = occurances.get(index);
            occurances.set(index, (List<Integer>) value);
            return oldValue;
        }
        fnames.add((String) key);
        occurances.add((List<Integer>) value);
        return null;
    }

    @Override
    public Object remove(Object key) {
        if (fnames.contains(key)){
            int index = fnames.indexOf(key);
            Object removedVal = occurances.get(index);
            occurances.remove(index);
            fnames.remove(index);
            return removedVal;
        }
        else
            return null;
    }

    @Override
    public void putAll(Map m) {
        TreeSet myKeys = new TreeSet(m.keySet());
        Collection myValues = new ArrayList(m.values());
        Object keys[] = new Object[myKeys.size()];
        keys = myKeys.toArray();
        for(int i=0; i<myKeys.size(); i++)
            put(keys[i], ((ArrayList) myValues).get(i));
    }

    @Override
    public void clear() {
        occurances.clear();
        fnames.clear();
    }

    @Override
    public Set keySet() {

        Set<String> mySet = new TreeSet<String>();
        for (int i=0; i<fnames.size(); i++)
            mySet.add(fnames.get(i));

        return mySet;
    }

    @Override
    public Collection values() {
        Collection<List<Integer>> myValues = new ArrayList<List<Integer>>();
        for(int i=0; i<occurances.size(); i++)
            myValues.add(occurances.get(i));
        return myValues;
    }

    @Override
    public Set<Entry> entrySet() {
        Set<Entry> myEntrySet = new TreeSet<>();
        for(int i=0; i<fnames.size(); i++)
        {
            Entry<String ,List<Integer>> temp;
            temp = new AbstractMap.SimpleEntry<String, List<Integer>>(fnames.get(i),occurances.get(i));
            myEntrySet.add(temp);
        }
        return myEntrySet;
    }

    public void print(){
        System.out.println("Keys: "+fnames);
        System.out.println("Values: "+occurances);
    }

    @Override
    public String toString() {

        String temp = new String();
        temp = "Keys: " + fnames.toString() + "\n" + "Values: " + occurances.toString() + "\n";

        return temp;
    }
}
