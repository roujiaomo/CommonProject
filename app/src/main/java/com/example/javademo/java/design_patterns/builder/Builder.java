package com.example.javademo.java.design_patterns.builder;

/**
 * 我们需要创建的对象的建造器Builder
 * 目的: 创建一个对象
 */
public class Builder {
    public String name;
    public int maxTotal;
    public int maxIdle;
    public int minIdle;

    public ResourcePoolConfig build(){
        //所创建对象的校验逻辑
        if(minIdle>maxTotal){
            //return
        }
        return new ResourcePoolConfig(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }
}
