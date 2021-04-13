package org.lecture.motive;

import lombok.Getter;
import lombok.ToString;

/**
 * subclass of abstract class Motive
 */
@Getter
@ToString(callSuper = true)
public class Landscape extends Motive implements MotiveBehavioral {

    private final Boolean cityView;

    public Landscape(String optimalAperture, String exposure, Boolean cityView) {
        super(optimalAperture, exposure);
        this.cityView = cityView;
        this.type = "Landscape";
    }

    /**
     * returns exposure of Landscape motive.
     * @return
     */
    public String getExposure() {
        return exposure;
    }

}