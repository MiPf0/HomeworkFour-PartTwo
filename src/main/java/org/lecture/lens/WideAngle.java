package org.lecture.lens;

import lombok.Getter;
import lombok.ToString;

/**
 * subclass of abstract class Lens
 */
@Getter
@ToString(callSuper = true)
public class WideAngle extends Lens implements LensBehavioral {

    private final Double distortionFactor;

    public WideAngle(String manufacturer, String aperture, Double distortionFactor) {
        super(manufacturer, aperture);
        this.distortionFactor = distortionFactor;
    }

    /**
     * Method that cleans the lens of the wideangle lens.
     */
    @Override
    public void cleanLens() {
        System.out.println("WideAngle-Lens cleaned.");
    }

    /**
     * Method that adjusts the lens of the wideangle lens.
     */
    @Override
    public void adjustLens() {
        System.out.println("WideAngle-Lens adjusted.");
    }
}