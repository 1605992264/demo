package com.yexiao.demo;


import com.yexiao.demo.factory.Dog;
import com.yexiao.demo.factory.DogEnum;
import com.yexiao.demo.factory.DogFactory;
import javafx.util.Callback;
import org.aspectj.weaver.ast.Var;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author xuhf
 * @date 2020/8/21 16:55
 **/
public class Main {

    public static void main(String[] args) {
        Dog dog = DogFactory.newDog(DogEnum.dog1);
        System.out.println(dog.getName());


    }



}


