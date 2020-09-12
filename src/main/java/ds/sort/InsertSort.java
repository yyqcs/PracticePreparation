package ds.sort;

/**
 * @author yyq
 */
public class InsertSort<T extends Comparable<T>> extends BaseSort<T> {
    /**
     * 向前走，起始点为i。
     * 每次都将当前元素插入到已经有序的数组中,第一个元素有序，从第二个开始。
     *
     * @param nums 待排序元素，必须是可比较的
     */
    @Override
    public void sort(T[] nums) {
        int length=nums.length;
        for(int i=1;i<length;i++){
            for(int j=i;j>0&&less(nums[j],nums[j-1]);j--){
                swap(nums,j,j-1);
            }
        }

    }
}
