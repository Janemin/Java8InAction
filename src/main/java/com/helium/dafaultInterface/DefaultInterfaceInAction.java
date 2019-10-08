package com.helium.dafaultInterface;


public class DefaultInterfaceInAction {

    public static void main(String[] args) {
        A a = () -> 10;
        System.out.println(a.size());
        System.out.println(a.isEmpty());
        System.out.println(A.getClazz());
    }

    private interface A {
        int size();

        default boolean isEmpty() {
            return size() == 0;
        }

        static Class getClazz() {
            return A.class;
        }
    }

}
