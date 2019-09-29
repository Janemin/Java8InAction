package com.helium.optional;

import java.util.Optional;

public class OptionalInAction {

    public static void main(String[] args) {

    }

    private static String getInsuranceNameByOptional(PersonOptional person) {
        return Optional.ofNullable(person)
                .flatMap(PersonOptional::getCarOptional)
                .flatMap(CarOptional::getInsurance)
                .map(Insurance::getName)
                .orElse("UNKNOWN");
    }
}
