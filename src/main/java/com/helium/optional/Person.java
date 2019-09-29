package com.helium.optional;

public class Person {

    private Car car;

    public Person() {

    }

    public Person(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
