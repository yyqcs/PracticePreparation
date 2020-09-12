package ds.sort;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> extends BaseSort<T> {

    /**
     * 先建堆，后交换,序号从0开始。
     * Floyd建堆算法，对所有的非叶节点进行下滤操作o(N)
     */
    @Override
    public void sort(T[] nums) {
        int length=nums.length-1;
        for(int k=length/2;k>=0;k--){
            shiftDown(nums,k,length);
        }
        while (length>0){
            swap(nums,0,length--);
            //最大元素，交换。
            shiftDown(nums,0,length);
        }
        System.out.println(Arrays.toString(nums));
    }
    private  void shiftDown(T[] nums,int k,int N){
        while (2*k+ 2<=N){
            //先选择孩子中最大的那个，然后与父节点比较。
            int j=2*k+1;
            if(k<N&&less(nums,j,j+1)){
                j++;
            }
            if(!less(nums,k,j)){
                break;
            }
            swap(nums,k,j);
            k=j;
        }
    }
    private boolean less(T[] nums, int i, int j) {
        return nums[i].compareTo(nums[j]) < 0;
    }

    public static void main(String[] args) {
        HeapSort<Integer> heapSort=new HeapSort<>();
        Integer[] nums={5,2,5,53,6,2,1};
        heapSort.sort(nums);
        System.out.println(Arrays.toString(nums));

    }

}
