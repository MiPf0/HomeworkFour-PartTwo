package org.lecture.motive;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Representation of a motive = abstract class and not instantiable
 */
@Getter
@EqualsAndHashCode
public abstract class Motive implements MotiveBehavioral {

    protected final String optimalAperture;
    protected final String exposure;
    protected String type;

    /**
     * constructor method
     * @param optimalAperture configure optimal aperture
     * @param exposure time needed to take the picture in seconds
     * @param type type of motive
     */
    protected Motive(String optimalAperture, String exposure, String type) {
        this.optimalAperture = optimalAperture;
        this.exposure = exposure;
        this.type = type;
    }

    /**
     * method overloading: 2nd constructor for child classes to be able to set their types individually
     * @param optimalAperture configure optimal aperture
     * @param exposure time needed to take the picture in seconds
     */
    protected Motive(String optimalAperture, String exposure) {
        this.optimalAperture = optimalAperture;
        this.exposure = exposure;
    }

}