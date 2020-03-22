package com.example.javademo.java.design_patterns.factory.simple_factory;

import com.example.javademo.java.design_patterns.factory.Sample1;
import com.example.javademo.java.design_patterns.factory.Sample2;
import com.example.javademo.java.design_patterns.factory.Sample3;
import com.example.javademo.java.design_patterns.factory.SampleInterface;

import static com.blankj.utilcode.util.FileUtils.getFileExtension;

/**
 * 简单工厂模式
 *
 * 返回不同的简单对象 通常用于同一功能不同类别的 例如 不同媒体的解析器
 *
 * Java多态的体现:  Father obj = new Son();
 *
 *
 */
public class SampleFactory {

    public static SampleInterface createSample(int type) {
        if (type == 1) {
            return new Sample1();
        } else if (type == 2) {
            return new Sample2();
        } else {
            return new Sample3();
        }
    }
}
