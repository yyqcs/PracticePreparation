package ds.sort;

/**
 * @author yyq
 */
public class Bubble<T extends Comparable<T>>  extends BaseSort<T>{

    /**
     * 向后走
     * 从左到右，不断交换相邻的逆序元素。i控制循环次数，j控制本轮的比较
     */
    @Override
    public void sort(T[] nums) {
        int length=nums.length;
        boolean sorted=false;
        for(int i=length-1;i>0&&!sorted;i--){
            sorted=true;
            for(int j=0;j<i;j++){
                if(less(nums[j+1],nums[j])){
                    sorted=false;
                    swap(nums,j,j+1);
                }
            }

        }

    }
}
