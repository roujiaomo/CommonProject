package com.example.javademo.java.design_patterns.factory.simple_factory;

import com.example.javademo.java.design_patterns.factory.Sample1;
import com.example.javademo.java.design_patterns.factory.Sample2;
import com.example.javademo.java.design_patterns.factory.Sample3;

public class Test {
    public static void main(String[] args) {
        try {
            Sample1 sample1 = (Sample1)SampleFactory.createSample(1);
            Sample2 sample2 = (Sample2)SampleFactory.createSample(1);
            Sample3 sample3 = (Sample3)SampleFactory.createSample(1);
        }catch (Exception e ){

        }
    }
}
