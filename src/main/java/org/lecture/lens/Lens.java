package org.lecture.lens;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Representation of a lens = abstract class and not instantiable
 */
@Getter
@EqualsAndHashCode
public abstract class Lens implements LensBehavioral {

    protected final String manufacturer;
    protected final String aperture;

    /**
     *
     * @param manufacturer the manufacturer
     * @param aperture fixed aperture
     */
    public Lens(String manufacturer, String aperture) {
        this.manufacturer = manufacturer;
        this.aperture = aperture;
    }
}