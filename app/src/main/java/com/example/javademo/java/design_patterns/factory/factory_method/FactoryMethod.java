package com.example.javademo.java.design_patterns.factory.factory_method;


import com.example.javademo.java.design_patterns.factory.SampleInterface;

/**
 * 工厂方法模式
 *
 * 应用于当所需要的创建的对象, 创建的逻辑比较复杂的时候(例如构造函数参数里 涉及其他对象)
 *
 * 这个时候简单工厂模式会使工厂里的创建部分的代码过于臃肿(if return else return)
 *
 * 工厂方法模式 一个工厂只创建这一类对象 , 然后工厂存储类里存储这些工厂的对象(详情在 SampleFactoryStore)
 *
 * 可以理解成为简单工厂再套一层简单工厂
 */

public interface FactoryMethod {
    public SampleInterface createSample();
}
