### 哈希表:将对象转换为索引，然后存储在数组中。

定义注意点：

- **对象**：就是面向对象中的对象，可以为任何东西。整数、浮点数、日期、字符串、类。
- **转换**：通过`hash函数`来完成，`hash函数`是hash表的核心与难点。对于整数，可以将`取模运算`作为`hash函数`。
- **数组**：hash表本质是就是一个数组(`静态、动态`)，这也是名称中"表"的含义。

### 体现的计算机思想： 空间换时间

思考角度，当空间无限时，可以使用O(1)完成各项操作，当空间只要1个时，就退化为线性表O(n)。

### 哈希表关注的核心问题

- **函数函数如何设计**
- **如何解决hash冲突**

> 对于不同的关键字得到了同一个hash地址，这种现象称为hash冲突(collision),形式化为：`key1≠key2,f(key1)==f(key2)`,其中`f`为hash函数。

### hash函数的设计原则

- **一致性**：如果`a==b`,则`hash(a)==hash(b)`,这是java自定义类时必须需重写的`hashcode方法`原因。

- **高效性**：计算高效便捷，O(1),这也是使用动态数组，在适当的情况下resize的原因。

- **均匀性**：**哈希值的分布越均匀越好**，**这就是取模法中模为质数的原因**。

### 整数转换为索引的方法：取模法

`hashcode=val%M`,**其中M为一个质数**，M的参考取值请[点击这儿](https://planetmath.org/goodhashtableprimes)（可能需要科学上网）。注意，公式总`val`为正整数，如果类型为`int`,可以先进行去除符号操作：`val=val&ox7fffffff`。因为从二进制的角度看`ox7fffffff`就是0和31个1，正好把符号位过滤掉。

### 任何对象都可以表示为整数。

- **浮点数**：在计算机内部都是用32位或者64位二进制表示，从整数的角度去解析这些位，就找到了浮点数对应的整数。
- **字符串**:**字符串本质上可以理解为B(base)进制数，其中B可以是不同字符串的个数**。例如26。也可以是任意设定的一个质数。
  - 例如：`code=c*26^3+o*26^2+d*26^1+e*26^0`
  - 例如：`abcd=a*B^3+b*B^2+c*B^1+d*B^0`，

**进制表示的形式简化以及编程实现：**

> hash(code)=(`c*B^3+o*B^2+d*B^1+e*B^0`)%M,可以表示为每一位乘以base，在加下一位
>
> =`((((c*B+o)*B+d)*B+e)%M`,**很重要，在java字符串的hashcode方法中B=31**
>
> =`((((c%M)*B+o)%M*B+d)%M*B+e)%M`,取余操作可以拿到括号里面去。(此性质快速幂算法中很常用)

```java
int hash=0;
for(int i=0;i<s.length;i++){
    hash=(hash*B+s.charAt(i))%M;
}
//java中B的是31，不在乎是否溢出，只要返回的是一个整数就OK,不知道M是什么，所以就没有出现M。
```

- **日期类型**：**考虑每个部分，每部分表示不同的权重**(进制思维)。
  - Date: year,month,day,则`hash(date)=(((date.year%M)*B+date.month%M)*B+date.day)%M`

- **类**：**分别将类的每一个字段当做B进制中的某一位**。依据B进制数进行转换。

### 当将自定义的类作为hashmap和hashSet的Key时，必须重写hashcode方法和equal方法。

**1.因为默认的hashcode()方法取==对象的地址==为基础获得的**，而new()同一类的不同实例对象地址不同，使得hashcode的结果也不同，**这就不满足一致性**，例如，new Person("小明")两次，它们的hashcode不同，但这显然就不合理。

**2.重写hashcode()只是为了获得正确的hash值，但当冲突了，还需要逐个字段进行比较才能确定是否相等**，这就要求重写equal来完成，因为默认的equal就等于`==`，含义为比较对象地址。

### 自定义hashcode和equals的实例

基本思路利用已有基本类型的包装类和String类的`hashcode()`方法来生成我们的`hashcode()`

```java
public class Student {
    Integer grade;
    Integer cls;
    String name;
//省去无关代码
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
```
完整代码以及测试用例，请[点击这儿](../../../../java/ds/hashtable/Student.java)。

### hash冲突的解决方法：链地址法

**数组中每个元素保留的是地址**。数组中每个元素的位置是`N%M`

去掉符号位：`hashcode(k1)&0X7FFFFFFF`

动态空间(**扩容和缩容**)处理`N/M>=upperTol`和`N/M<lowerTol`

### 实现自己的hashtable，采用TreeMap作为链接冲突元素的容器

都是先获得key索引，然后再获某个元素。`TreeMap<K,V> map=hashtable[hash(key)]`。

完整源码及测试代码请[点击这儿](../../../../java/ds/hashtable/MyHashTable.java)。

### 更多关于hash冲突的办法

1. 开放地址法。
2. 再哈希法：rehashing.

