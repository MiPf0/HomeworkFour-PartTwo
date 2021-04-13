package org.lecture.camera;

import lombok.Getter;
import lombok.ToString;
import org.lecture.lens.Lens;

/**
 * subclass of abstract class Camera
 */
@Getter
@ToString(callSuper = true)
public class Sony extends Camera implements CameraBehavioral {

    private final Double sonyAlphaVersion;

    public Sony(String colour, boolean hasLivePicture, Lens lens, Double sonyAlphaVersion) {
        super(colour, hasLivePicture, lens);
        this.sonyAlphaVersion = sonyAlphaVersion;
        this.name = "Sony";
    }

    /**
     * Method that prints out to the user the info which sonyAlphaVersion the Sony Cam has.
     */
    private void printSonyAlphaVersion() {
        System.out.println("α" + sonyAlphaVersion);
    }
}