package com.example.javademo.java.oop.polymorphism;

import com.example.javademo.java.LogUtils;

/**
 * 多态
 *
 * 基于jvm原理:
 * 例如 : List mList = new ArrayList();
 * 编译期(语法合理) : 识别对象为父类, 调用方法时, 识别父类存在该方法即编译通过
 * 运行期(符合Java设计) : 识别new出的对象到底是哪个, 即引用到底指向谁
 *
 * <p>
 * 子类可以替换父类, 父类引用指向子类对象:
 *
 * <p>
 * 调用方法时 :
 * 首先检查父类有无该方法, 如果没有则编译失败
 * 如果父类有该方法, 检查子类有无该方法
 * 如果子类也有该方法, 则调用子类方法, 否则调用父类方法 (注: super关键字调用父类方法)
 */
public class SampleList {
    public void init() {
        //执行父类方法
        LogUtils.println("调用父类 init");
    }
}

class SampleArrayList extends SampleList {
    @Override
    public void init() {
        //super相当于是指向当前对象的父类，这样就可以用super.xxx来引用父类的成员。
        super.init();
        LogUtils.println("调用子类 init");
    }
}
