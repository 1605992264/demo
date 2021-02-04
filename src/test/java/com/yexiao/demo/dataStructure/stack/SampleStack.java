package com.yexiao.demo.dataStructure.stack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xuhf
 * @date 2021/1/26 13:55
 **/
public class SampleStack<T> {


    private List<T> stack;

    public SampleStack(){
        stack = new LinkedList<>();
    }

    public int size(){
        return stack.size();
    }

    public void add(T t){
        stack.add(t);
    }

    public T push(){
        return stack.remove(stack.size()-1);
    }

}
