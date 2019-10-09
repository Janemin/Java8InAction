package com.helium.dafaultInterface;

public class MultipleInheritance {

    public static void main(String[] args) {
        new ClazzA().hello();
        new ClazzB().hello();
        new ClazzC().hello();
    }

    private interface A {
        default void hello() {
            System.out.println("A.hello");
        }
    }

    private interface B extends A {
        @Override
        default void hello() {
            System.out.println("B.hello");
        }
    }

    private interface C extends A {
        @Override
        default void hello() {
            System.out.println("B.hello");
        }
    }

    private static class ClazzA implements B {

    }

    private static class ClazzB implements A, B {

    }

    private static class ClazzC implements C, B {
        @Override
        public void hello() {
//            B.super.hello();
            System.out.println("ClazzC.hello");
        }
    }

}
