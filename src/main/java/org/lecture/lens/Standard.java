package org.lecture.lens;

import lombok.Getter;
import lombok.ToString;

/**
 * subclass of abstract class Lens
 */
@Getter
@ToString(callSuper = true)
public class Standard extends Lens implements LensBehavioral {

    private final Boolean appropriateForStreetPhotography;

    public Standard(String manufacturer, String aperture, Boolean appropriateForStreetPhotography) {
        super(manufacturer, aperture);
        this.appropriateForStreetPhotography = appropriateForStreetPhotography;
    }

    /**
     * Method that cleans the lens of the standard lens.
     */
    @Override
    public void cleanLens() {
        System.out.println("Standard-Lens cleaned.");
    }

    /**
     * Method that adjusts the lens of the standard lens.
     */
    @Override
    public void adjustLens() {
        System.out.println("Standard-Lens adjusted.");
    }
}