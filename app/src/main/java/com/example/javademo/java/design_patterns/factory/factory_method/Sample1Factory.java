package com.example.javademo.java.design_patterns.factory.factory_method;


import com.example.javademo.java.design_patterns.factory.Sample1;
import com.example.javademo.java.design_patterns.factory.SampleInterface;

/**
 *
 */
public class Sample1Factory implements FactoryMethod{

    @Override
    public SampleInterface createSample() {
        return new Sample1();
    }
}
