package com.example.javademo.java.design_patterns.builder;

/**
 * 建造者模式 Builder模式
 * <p>
 * 作用 : 创建对象
 * <p>
 * 在简单的对象创建中, 我们可以使用构造方法构建对象, 使用get,set传值
 * 构造函数缺陷: 当必传的参数过多时, 会出现参数顺序很难处理的问题 ,构造方法过长的问题
 * get,set方式缺陷: 暴露了过多公共方法
 * <p>
 * 如果我们希望创建完对象不再改动, 便不能暴露get, set方法, 这时Builder模式就派上用场了.
 *
 * 与工厂模式区别:
 * Builder模式主要用于构建一个复杂对象, 且不允许操作对象属性
 * Factory模式应用于创建同一类型(继承不同父类的子类), 将创建逻辑与实现逻辑分离的
 */
public class ResourcePoolConfig {

    /**
     * 属性都是私有 不设置相应get, set方法
     * 即不让对象持有者 操作对象属性
     * 完全通过外部传入Builder
     */
    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;

    public ResourcePoolConfig(Builder builder) {
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
    }

}
