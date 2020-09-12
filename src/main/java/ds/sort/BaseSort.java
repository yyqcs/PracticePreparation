package ds.sort;

/**
 * @author yyq
 */
public abstract class BaseSort<T extends Comparable<T>> {
    /**
     * 对nums进行排序
     * @param nums 待排序元素，必须是可比较的
     */
    public abstract void sort(T[] nums);

    protected boolean less(T v,T w){
        return v.compareTo(w)<0;
    }
    protected void swap(T[] a,int i,int j){
        T t=a[i];
        a[i]=a[j];
        a[j]=t;
    }

}
