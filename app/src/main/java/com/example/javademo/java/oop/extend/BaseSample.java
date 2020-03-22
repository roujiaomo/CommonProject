package com.example.javademo.java.oop.extend;
/**
 * java 继承
 *
 * 优势 : 子类可以共用父类的属性, 减少了很多声明(例如我们总是抽出基类)
 * 缺点 : 嵌套过多, 父类过多会使代码可读性变差, 并且父类子类高耦合, 修改父类直接影响子类
 */
public class BaseSample {

    public void getObj(){

    }

    private void setObj(){

    }

}

class Sample extends BaseSample{

    @Override
    public void getObj() {
        super.getObj();
    }

    /**
     * 子类可以拓展自己的方法
     */
    private void doSth(){

    }
}