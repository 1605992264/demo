package com.yexiao.demo.factory;


/**
 * @author xuhf
 * @date 2020/8/21 16:55
 **/
public class Main {

    public static void main(String[] args) {
        Dog dog = DogFactory.newDog(DogEnum.dog1);
        Dog dog2 = DogFactory.newDog(DogEnum.dog1);
        System.out.println(dog.equals(dog2));


    }



}


