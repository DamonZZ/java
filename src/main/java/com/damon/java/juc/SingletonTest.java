package com.damon.java.juc;

public class SingletonTest {

    public static void main(String[] args) {

    }

}

// 1.
class Hungry {

    private static final Hungry HUNGRY = new Hungry();

    // may waste memory
    private byte[] data1 = new byte[1024 * 1024];
    private byte[] data2 = new byte[1024 * 1024];
    private byte[] data3 = new byte[1024 * 1024];
    private byte[] data4 = new byte[1024 * 1024];

    private Hungry() {

    }

    public static Hungry getInstance() {
        return HUNGRY;
    }
}

// 2.
class LazyMan {

    private static volatile LazyMan lazyMan;

    private LazyMan() {

    }

    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan(); // not a atomic operation
                    /*
                        1. assign memory
                        2. init object
                        3. assign the object to the memory
                     */
                }
            }
        }
        return lazyMan; // at this time the object is not init completely, so need volatile
    }

}

// 3. static inner class
class StaticInner {

    public static class InnerClass {
        private static final StaticInner staticInner = new StaticInner();
    }

    private StaticInner() {

    }

    public static StaticInner getInstance() {
        return InnerClass.staticInner;
    }

}

// 4. Enum class
enum EnumClass {

    INSTANCE;

    public EnumClass getInstance() {
        return INSTANCE;
    }

}
