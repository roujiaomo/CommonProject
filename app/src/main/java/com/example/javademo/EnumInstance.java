package com.example.javademo;

/**
 * 《 Effective java 》推荐
 * 枚举类天然可序列化，受到其他类保护不会被多次序列化
 * 所以可以自然防御序列化反序列化及反射攻击
 */
public enum EnumInstance {

    INSTANCE {
        protected void printTest() {
            System.out.println("Print Test");
        }
    };

    protected abstract void printTest();

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumInstance getInstance() {
        return INSTANCE;
    }

}
