package com.example.javademo.java.design_patterns.factory.factory_method;

public class Test {
    public static void main(String[] args) {
        FactoryMethod factoryMethod = SampleFactoryStore.getFactory(1);
        factoryMethod.createSample().init();
    }
}
