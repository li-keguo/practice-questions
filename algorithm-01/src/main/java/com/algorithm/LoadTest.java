package com.algorithm;

public class LoadTest {

    static {
        System.out.println("LoadTest static");
    }

    {
        System.out.println("LoadTest {}");
    }

    public LoadTest() {
        System.out.println("LoadTest");
    }

    static class Inners {
        static {
            System.out.println("LoadTest.Inners static");
        }

        {
            System.out.println("LoadTest.Inners {}");
        }

        public Inners() {
            System.out.println("LoadTest.Inners");
        }
    }

    class Inner {

        {
            System.out.println("LoadTest.Inner {}");
        }

        public Inner() {
            System.out.println("LoadTest.Inner");
        }
    }

    public static void main(String[] args) {
        Child child = new Child();

    }
}

class Child extends LoadTest {
    static {
        System.out.println("Child static");
    }

    {
        System.out.println("Child {}");
    }

    static class Inners {
        static {
            System.out.println("Child.Inners static");
        }

        {
            System.out.println("Child.Inners {}");
        }

        public Inners() {
            System.out.println("Child.Inners");
        }
    }

    class Inner {

        {
            System.out.println("Child.Inner {}");
        }

        public Inner() {
            System.out.println("Child.Inner");
        }
    }

    public Child() {
        System.out.println("Child");
    }
}
