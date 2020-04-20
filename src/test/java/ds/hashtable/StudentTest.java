package ds.hashtable;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void  withoutOverRide(){
        Student student1=new Student(2,3,"小明");
        Student student2=new Student(2,3,"小明");
        assertEquals(student1.hashCode(),student2.hashCode());//会报错
        assertTrue(student1.equals(student2));
    }
    @Test
    public void OnlyOverrideHashCode(){
        Student student1=new Student(2,3,"小明");
        Student student2=new Student(2,3,"小明");
        assertEquals(student1.hashCode(),student2.hashCode());//OK.
        assertTrue(student1.equals(student2));//报错
    }
    @Test
    public void OverrideBoth(){
        Student student1=new Student(2,3,"小明");
        Student student2=new Student(2,3,"小明");
        assertEquals(student1.hashCode(),student2.hashCode());//OK.
        assertTrue(student1.equals(student2));//OK

    }

}