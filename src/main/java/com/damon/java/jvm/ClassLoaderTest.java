package com.damon.java.jvm;

public class ClassLoaderTest {

    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        Class<? extends Car> carClass1 = car1.getClass();
        Class<? extends Car> carClass2 = car2.getClass();
        Class<? extends Car> carClass3 = car3.getClass();

        System.out.println(carClass1);
        System.out.println(carClass2);
        System.out.println(carClass3);

        ClassLoader classLoader = carClass1.getClassLoader();
        System.out.println(classLoader); // AppClassLoader
        System.out.println(classLoader.getParent()); // ExtClassLoader   \jre\lib\ext\
        System.out.println(classLoader.getParent().getParent()); //null  \jre\lib\rt.jar
    }

}


class Car {

}