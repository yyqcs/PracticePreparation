package ds.sort;

/**
 * @author yyq
 * 一个元素始终是有许的！！
 */
public class SelectionSort<T extends Comparable<T>> extends BaseSort<T> {
    @Override
    public void sort(T[] nums) {
        int length=nums.length;
        for(int i=0;i<length-1;i++){
            int min=i;
            for(int j=i+1;j<length;j++){
                if(less(nums[j],nums[min])){
                    min=j;
                }
            }
            swap(nums,i,min);
        }


    }
}
