package com.example.javademo.java.design_patterns.builder;

public class Test {
    public static void main(String[] args) {
        //创建一个普通 ResourcePoolConfig 对象 作用很小
        ResourcePoolConfig resourcePoolConfig = new ResourcePoolConfig(new Builder());

        //创建一个修改了默认参数的 ResourcePoolConfig 对象, 使用builder方式
        Builder builder = new Builder();
        builder.setName("name");
        builder.setMaxTotal(16);
        builder.setMinIdle(12);
        ResourcePoolConfig resourcePoolConfig2 = builder.build();

        //将Builder类写在对象的里面作为静态内部类 即可这样链式调用
//        ResourcePoolConfig config = new Builder().
//                setName("name").
//                setMaxTotal(16).
//                setMaxIdle(10).
//                setMinIdle(12).
//                build();
    }
}
