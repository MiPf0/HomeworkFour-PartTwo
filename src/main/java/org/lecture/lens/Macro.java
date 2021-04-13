package org.lecture.lens;

import lombok.Getter;
import lombok.ToString;

/**
 * subclass of abstract class Lens
 */
@Getter
@ToString(callSuper = true)
public class Macro extends Lens implements LensBehavioral {

    private final Boolean appropriateForInsects;

    public Macro(String manufacturer, String aperture, Boolean appropriateForInsects) {
        super(manufacturer, aperture);
        this.appropriateForInsects = appropriateForInsects;
    }

    /**
     * Method that cleans the lens of the macro lens.
     */
    @Override
    public void cleanLens() {
        System.out.println("Macro-Lens cleaned.");
    }

    /**
     * Method that adjusts the lens of the macro lens.
     */
    @Override
    public void adjustLens() {
        System.out.println("Macro-Lens adjusted.");
    }
}