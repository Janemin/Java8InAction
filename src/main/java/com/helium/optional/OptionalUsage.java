package com.helium.optional;

import java.util.Optional;

public class OptionalUsage {

    public static void main(String[] args) {

        Optional<Insurance> insuranceOptional = Optional.empty();
        // java.util.NoSuchElementException: No value present
        insuranceOptional.get();

        Optional<Insurance> insuranceOptional1 = Optional.of(new Insurance());
        insuranceOptional1.get();

        Optional<Insurance> insuranceOptional2 = Optional.ofNullable(null);
        insuranceOptional2.get();

        // get
        insuranceOptional.orElseGet(Insurance::new);

        insuranceOptional.orElse(new Insurance());

        insuranceOptional.orElseThrow(RuntimeException::new);

        insuranceOptional.orElseThrow(() -> new RuntimeException("No such element"));

        insuranceOptional.filter(insurance -> insurance.getName() != null).get();

        System.out.println(getInsuranceNameByOptional(new Person()));
    }

    private static String getInsuranceNameByOptional(Person person) {
        return Optional.ofNullable(person)
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("UNKNOWN");
    }
}
