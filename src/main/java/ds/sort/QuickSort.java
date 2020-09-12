package ds.sort;

import java.util.Arrays;

public class QuickSort<T extends Comparable<T>> extends BaseSort<T> {
    /**
     * 对nums进行排序
     *
     * @param nums 待排序元素，必须是可比较的
     */
    @Override
    public void sort(T[] nums) {
        int length=nums.length;
        sort(nums,0,length-1);
    }
    private void sort(T[] nums,int low,int hi){
        if(low>=hi){
            return ;
        }
        int p=partition(nums,low,hi);
        //p的位置已经有序了！！
        sort(nums,low,p-1);
        sort(nums,p+1,hi);
    }

    /**
     *
    返回p, 使得arr[l...p-1] <= arr[p] ; arr[p+1...r] >= arr[p]
     */
    private int partition(T[] nums, int l, int h) {
        int i = l, j = h + 1;
        T v = nums[l];
        while (true) {
            while (i<h&&less(nums[++i], v) ) {}
            while (j>l&&less(v, nums[--j])) {}
            //pivot的含义
            if (i >= j) {
                break;
            }

            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    /**
     * 在数组中选择第k大元素。
     */
    public T quickSelect(T[]nums,int k){
        int low=0,hi=nums.length-1;
        while (hi>low){
            int j=partition(nums,low,hi);
            if(j==k){
                return nums[k];
            }
            else if(j>k){
                hi=j-1;
            }else {
                low=j+1;
            }
        }
        return nums[k];
    }

    public static void main(String[] args) {
        QuickSort<Integer> quickSort=new QuickSort<>();
        Integer[] nums={5,2,5,53,6,2,1};
        Integer e=quickSort.quickSelect(nums,656);
        System.out.println(e);
        quickSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}

public int partition(int nums,int low,int high){
	int pivot=nums[low];
	while(low<high){
		while(low<high&&nums[high]>=pivot){
			high--;
		}
		//找到pivot右边的元素，它不满足要求
		nums[low]=nums[high];
		while(low<high&&nums[low]<=pivot){
			low++;
		}
		nums[high]=nums[low];
	}
}