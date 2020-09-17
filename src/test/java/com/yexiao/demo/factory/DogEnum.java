package com.yexiao.demo.factory;

/**
 * @author xuhf
 * @date 2020/9/15 15:37
 **/
public enum DogEnum {
    /**
     *枚举默认 final static 单例
     * new dog() 可换成全限定类名 通过反射来创建
     * */
    dog1(new Dog1(),"dog1"),
    dog2(new Dog2(),"dog2"),
    dog3(new Dog3(),"dog3");

    private Dog dog;
    private String name;

     DogEnum(Dog dog,String name){
        this.dog = dog;
        this.name = name;
    }

    public Dog getDog() {
        return dog;
    }

}
