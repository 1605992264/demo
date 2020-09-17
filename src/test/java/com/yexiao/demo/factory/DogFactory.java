package com.yexiao.demo.factory;

/**
 * @author xuhf
 * @date 2020/9/15 15:41
 **/
public class DogFactory {

    private DogFactory(){ }

    public static Dog newDog(DogEnum dogEnum){
        return dogEnum.getDog();
    }

}
