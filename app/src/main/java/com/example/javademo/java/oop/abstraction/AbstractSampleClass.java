package com.example.javademo.java.oop.abstraction;

import com.example.javademo.java.LogUtils;

/**
 * 抽象
 * <p>
 * 抽象这个概念, 代表着不可直接实例化
 * 即不具体实现, 而 {} 为方法体的实现
 * <p>
 * 应用于Java三大特性
 * 比如当源码过于繁重 只需要了解其方法作用 而不用关注具体实现
 * 再比如基类的设计
 * <p>
 * 可以基于抽象类和接口完成
 *
 * 抽象类和接口共同点:
 * 自身都不可以实例化, 子类若实例化必须实现其未实现的方法(抽象方法,接口里声明的方法)
 *
 * 抽象类和接口不同点:
 * 1.抽象类可以有非抽象方法和私有属性, 接口只能声明方法
 * 2.子类继承抽象类只需实现抽象方法, 子类实现接口需要实现全部方法
 * 3. extend / implements
 */
public class AbstractSampleClass {
    public static void main(String[] args) {

        SampleUtil sampleUtil = new SampleUtil();
        //实例化接口时, 必须实现其内部的方法(此处为匿名) new EventCallBack()为一个对象
        EventCallBack eventCallBack = new EventCallBack() {
            @Override
            public void onClickListener() {
                //当接口对象持有者 SampleUtil调用 eventCallBack.onClickListener()后
                //调用的是接口实现类的该方法 完成回调(可以类比为调用父类方法, 走的是子类的)
            }
        };
        sampleUtil.setEventCallBack(eventCallBack); //将接口对象传给SampleUtil
        sampleUtil.init(); //接口实例持有者 SampleUtil调用 eventCallBack.onClickListener();
    }
}


/**
 * 抽象类 多用于 代码复用
 */
abstract class BaseUtil {
   private String data;
   public abstract void init();
}

class SampleUtil extends BaseUtil{

    //让接口成为一个对象的属性
    private EventCallBack eventCallBack;

    @Override
    public void init() {
        eventCallBack.onClickListener();
    }

    public void setEventCallBack(EventCallBack eventCallBack) {
        this.eventCallBack = eventCallBack;
    }
}
/**
 * 接口 多用于 代码解耦
 */
interface EventCallBack {
    void onClickListener();
}

class SampleCallBack implements EventCallBack{

    @Override
    public void onClickListener() {

    }
}