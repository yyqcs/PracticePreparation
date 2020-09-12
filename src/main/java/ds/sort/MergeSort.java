package ds.sort;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.reflect.Array.*;

/**
 * @author yyq
 */
public class MergeSort<T extends Comparable<T>> extends BaseSort<T> {
    /**
     * MergeSort需要两个接口
     *
     * @param nums 待排序元素，必须是可比较的
     */
    @Override
    public void sort(T[] nums) {
        int length=nums.length;
        T[] aux=(T[])new Comparable[length];
        sort(nums,aux,0,length-1);
    }
    private void sort(T[] nums,T[] aux,int low ,int hi){
        //优化1，if(hi-low<=15)时，进行insertSort
        if(low>=hi){
            return;
        }
        int mid=low+(hi-low)/2;
        sort(nums,aux,low,mid);
        sort(nums,aux,mid+1,hi);
        // 优化1: 对于arr[mid] <= arr[mid+1]的情况,不进行merge
        if(nums[mid+1].compareTo(nums[mid])<0){
            merge(nums,aux,low,mid,hi);
        }


    }

    /***
     * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
     */
    private void merge(T[]nums,T[] aux,int low,int mid,int hi){
        int i=low,j=mid+1,k=low;
        while (i<=mid&&j<=hi){
            if(nums[i].compareTo(nums[j])<=0){
                aux[k++]=nums[i++];
            }else{
                aux[k++]=nums[j++];
            }
        }
        while (i<=mid){
            aux[k++]=nums[i++];
        }
        while (j<=hi){
            aux[k++]=nums[j++];
        }
        //回写到原数组中
        for(i=low;i<=hi;i++) {
            nums[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        MergeSort<Integer> mergeSort=new MergeSort<>();
        Integer[] nums={3,2,4,1};
        mergeSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
