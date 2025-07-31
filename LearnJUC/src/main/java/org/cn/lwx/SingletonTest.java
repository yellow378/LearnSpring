package org.cn.lwx;

public class SingletonTest {
    private Integer a = 10;
    public SingletonTest() {
        this.a = 10;
    }
    public void Print(){
        System.out.println(a);
    }
    private static volatile SingletonTest instance;
    public static SingletonTest getInstance(){
        if(instance == null){
            synchronized (SingletonTest.class){
                if(instance == null){
                    instance = new SingletonTest();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        SingletonTest singletonTest = SingletonTest.getInstance();
        singletonTest.Print();
    }
}
