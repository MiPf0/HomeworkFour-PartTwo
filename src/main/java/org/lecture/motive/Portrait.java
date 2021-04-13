package org.lecture.motive;

import lombok.Getter;
import lombok.ToString;

/**
 * subclass of abstract class Motive
 */
@Getter
@ToString(callSuper = true)
public class Portrait extends Motive implements MotiveBehavioral {

    protected final Double lengthOfHair;

    public Portrait(String optimalAperture, String exposure, Double lengthOfHair) {
        super(optimalAperture, exposure);
        this.type = "Portrait";
        this.lengthOfHair = lengthOfHair;
    }

}
