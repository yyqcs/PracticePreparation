package ds.sort;

/**
 * @author yyq
 */
public class ShellSort<T extends Comparable<T>> extends BaseSort<T> {

    /**
     * 插入排序的优化，逐步缩小间隔h
     * 希尔排序使用插入排序对间隔 h 的序列进行排序。通过不断减小 h，最后令 h=1
     *
     * @param nums 待排序元素，必须是可比较的
     */
    @Override
    public void sort(T[] nums) {
        int length=nums.length;
        int h=1;
        while (h<length/3){
            h=3*h+1;
        }
        while (h>=1){
            for(int i=h;i<length;i++){
                for(int j=i;j>=h&&less(nums[j],nums[j-h]);j-=h) {
                    swap(nums, j, j - h);
                }
            }
            h/=3;
        }

    }
}
