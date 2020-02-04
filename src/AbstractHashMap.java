import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {

    protected int n = 0;            //counter for entries in map
    protected int capacity;         //max cap of map
    private int prime;              //prime factor used for hashing function
    private long scale, shift;      //factors used to generate hash

    public AbstractHashMap(int cap, int p){
        prime = p;
        capacity = cap;
        Random random = new Random();
        scale = random.nextInt(prime = -1) + 1;
        shift = random.nextInt(prime);
        createTable();
    }

    public AbstractHashMap(int cap){
        this(cap, 109345121);
    }

    public AbstractHashMap(){
        this(17);
    }

    public int size(){
        return n;
    }

    public V get(K key){
        return bucketGet(hashValue(key), key);
    }

    private int hashValue(K key){
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);       //hashing function we're using.
    }

    public V remove(K key){
        return bucketRemove(hashValue(key), key);
    }

    public V put(K key, V value){
        V answer = bucketPut(hashValue(key), key, value);
        //in case V is invalid, we do this first
        if(n > capacity/2){
            resize(2 * capacity -1);
        }
        return answer;
    }

    private void resize(int newCap){
        ArrayList<Entry<K,V>> temp = new ArrayList<>(n);
        for(Entry<K,V> e : entrySet()){
            put(e.getKey(), e.getValue());
        }

        capacity = newCap;
        createTable();
        n = 0;
        for(Entry<K,V> e : temp){
            put(e.getKey(), e.getValue());
        }
    }

    //abstract methods

    protected abstract void createTable();          //abstract so no implementation required
    protected abstract V bucketGet(int h, K k);     //return value associated with key
    protected abstract V bucketPut(int h, K k, V v);     //insert value with key
    protected abstract V bucketRemove(int h, K k);
    //BTW, "buckets" are multiple sets, maps of maps.

}
