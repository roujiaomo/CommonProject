package com.example.javademo.java.oop.polymorphism;

public class Test {

    public static void main(String[] args) {
        FatherNewFather();
        FatherNewSon();
    }

    /**
     * 父类 new 父类对象
     */
    private static void FatherNewFather() {
        SampleList sampleList = new SampleList();
        sampleList.init();
    }

    /**
     * 父类 new 父类对象
     */
    private static void FatherNewSon() {
        SampleList sampleList = new SampleArrayList();
        sampleList.init();
    }
}
