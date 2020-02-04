public interface Map<K,V> {

    int size();             //how many keys in map

    boolean isEmpty();      //is the map empty

    V get(K key);           //all keys have to be unique. This returns the value associated with a key

    V put(K key, V value);  //put new value into map and tell us what the new value is/overwritten value was

    V remove(K key);        //remove value

    Iterable<K> keySet();     //return iterable collection of all keys currently in map (if we want to find out what keys are in the map)

    Iterable<V> valueSet();     //same as above, but for values

    Iterable<Entry<K,V>> entrySet();    //as above but for keys AND values

}
