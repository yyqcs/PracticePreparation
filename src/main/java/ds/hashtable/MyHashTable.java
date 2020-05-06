package ds.hashtable;

import java.util.TreeMap;

/**
 * 自己实现的hashtable,采用链地址法来解决冲突。
 * 使用TreeMap容纳冲突元素。
 * @author yyq
 * @version 1.0
 */
public class MyHashTable<K extends Comparable<K>,V> {
    /**
     * 取模法中，模通常为素数
     */
    private final int[]CAPACITY={
            53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 161061274
    };
    private static final int UPPER_TOL=10;
    private static final int LOWER_TOL=2;
    private int capacityIndex;

    /**
     * hashtable就是数组
     */
    private TreeMap<K,V>[] hashtable;
    private int size;
    private int M;

    public MyHashTable(int capacityIdx){
        assert capacityIdx>=0&&capacityIdx<CAPACITY.length;
        this.M=CAPACITY[capacityIdx];
        this.capacityIndex=capacityIdx;
        size=0;
        /**
         * 数组中每个元素仅仅是引用，想要分配空间，必须逐个NEW.
         * 可以看到，对于连地址而言，数组中每个元素都已分配空间，地址！！！
         */
        hashtable=new TreeMap[M];
        for(int i=0;i<M;i++){
            hashtable[i]=new TreeMap<>();
        }
    }
    public MyHashTable(){this(0);}

    public int hash(K key){
        return (key.hashCode()&0x7FFFFFFF)%M;
    }
    public int getSize(){
        return size;
    }

    /**
     * @param key 键
     * @param value 值
     *向数组中K对应的位置上放置值value,
     * 已包括，则更新。
     * 为包括，则新增。
     *当元素个数超过上界，则扩容.
     */
    public void put(K key,V value){
        TreeMap<K,V> map=hashtable[hash(key)];
        if(map.containsKey(key)){
            map.put(key,value);
        }else{
            map.put(key,value);
            size++;
            if(size>=UPPER_TOL*M&&capacityIndex+1<CAPACITY.length){
                capacityIndex++;
                resize(CAPACITY[capacityIndex]);
            }
        }
    }
    public V remove(K key){
        V ret=null;
        TreeMap<K,V> map=hashtable[hash(key)];
        if(map.containsKey(key)){
            ret=map.remove(key);
            size--;
            if(size<LOWER_TOL*M&&capacityIndex-1>=0){
                capacityIndex--;
                resize(CAPACITY[capacityIndex]);
            }

        }
        return ret;
    }
    public void set(K key,V value){
        TreeMap<K,V> map=hashtable[hash(key)];
        if(!map.containsKey(key)) {
            throw new IllegalArgumentException(key+"doesn't exist!");
        }
        map.put(key,value);
    }
    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }
    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    /**
     * @param newM 新的数组长度，hash空间
     *resize()的代价，原数据中的所有内容复制到新的数组中。
     */
    private void resize(int newM){
        TreeMap<K,V>[] newHashTable=new TreeMap[newM];
        for(int i=0;i<newM;i++){
            newHashTable[i]=new TreeMap<>();
        }
        int oldM=this.M;
        this.M=newM;
        for(int i=0;i<oldM;i++){
            TreeMap<K,V> map=hashtable[i];
            for(K key:map.keySet()){
                newHashTable[hash(key)].put(key,map.get(key));
            }
        }
        this.hashtable=newHashTable;
    }



}
