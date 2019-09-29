package com.helium.optional;

import java.util.Optional;

public class PersonOptional {

    private Optional<CarOptional> carOptional;

    public PersonOptional() {

    }

    public Optional<CarOptional> getCarOptional() {
        return carOptional;
    }

    public void setCarOptional(Optional<CarOptional> carOptional) {
        this.carOptional = carOptional;
    }
}
