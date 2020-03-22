package com.example.javademo.java.design_patterns.factory.factory_method;

import java.util.HashMap;

/**
 * 工厂的存储类
 */
public class SampleFactoryStore {

    private static final HashMap<Integer,FactoryMethod> factoryStoreHashMap = new HashMap<>();

    /**
     * 使用静态代码块的形式在类加载的时候就初始化好了 各个factory对象
     */
    static {
        factoryStoreHashMap.put(1,new Sample1Factory());
        factoryStoreHashMap.put(2,new Sample2Factory());
        factoryStoreHashMap.put(3,new Sample3Factory());
    }

    public static FactoryMethod getFactory(int type){
        FactoryMethod factory = factoryStoreHashMap.get(type);
        return factory;
    }


}
