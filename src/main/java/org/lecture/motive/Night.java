package org.lecture.motive;

import lombok.Getter;
import lombok.ToString;

/**
 * subclass of abstract class Motive
 */
@Getter
@ToString(callSuper = true)
public class Night extends Motive implements MotiveBehavioral {

    private final Integer numberOfLightSources;

    public Night(String optimalAperture, String exposure, Integer numberOfLightSources) {
        super(optimalAperture, exposure);
        this.type = "Night";
        this.numberOfLightSources = numberOfLightSources;
    }

}
