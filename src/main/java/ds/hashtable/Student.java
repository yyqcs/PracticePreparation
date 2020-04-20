package ds.hashtable;

public class Student {
    Integer grade;
    Integer cls;
    String name;

    public Student(Integer grade, Integer cls, String name) {
        this.grade = grade;
        this.cls = cls;
        this.name = name;
    }

    @Override
    public int hashCode() {//套路：模仿String，Base取31
        int B=31;
        int hash=0;
        hash=hash*B+grade.hashCode();
        hash=hash*B+cls.hashCode();
        hash=hash*B+name.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {//有套路,逐个字段比较
        if(this==obj) return true;
        if(obj==null)return false;
        if(this.getClass()!=obj.getClass()) return  false;
        Student another=(Student) obj;
        return this.grade.equals(another.grade) &&
                this.cls.equals(another.cls) &&
                this.name.equals(another.name);//字符串比较相等，equals
    }
}
