package com.helium.optional;

public class NullPointerExceptionIssue {

    public static void main(String[] args) {
        String insuranceName = getInsuranceNameByDeepDoubts(new Person());
    }

    private static String getInsuranceNameByMuitExit(Person person) {
        final String defaultValue = "UNKNOWN";
        if (person == null) {
            return defaultValue;
        }
        Car car = person.getCar();
        if (car == null) {
            return defaultValue;
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return defaultValue;
        }
        return insurance.getName();
    }

    private static String getInsuranceNameByDeepDoubts(Person person) {
        if (null != person) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "UNKNOWN";
    }

    private static String getInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }
}
