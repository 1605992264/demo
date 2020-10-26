package com.yexiao.demo.sort;

import java.util.Arrays;

/**
 * @author xuhf
 * @date 2020/10/21 13:46
 * 插入排序
 * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（
 * 如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
 **/
public class CRSort implements ISort {

    @Override
    public int[] sort(int[] nums) {
        int[] sort = Arrays.copyOf(nums, nums.length);
        for(int i = 1; i < sort.length ; i++){
            int temp = sort[i];
            int index = i;
            while ((index-1) >=0 && sort[index-1] > temp){
                sort[index] = sort[index-1];
                index--;
            }
            if(index != i){
                sort[index] = temp;
            }
        }
        return sort;
    }
}
