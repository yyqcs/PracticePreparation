package ds.hashtable;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashTableTest {
    @Test
    public void testBasic(){
        MyHashTable<String ,Integer> myHashTable=new MyHashTable<>();
        myHashTable.put("小明",24);
        myHashTable.set("小明",25);
        int a=myHashTable.get("小明");
        assertEquals(a,25);
    }

}