package org.lecture.camera;

import lombok.Getter;
import lombok.ToString;
import org.lecture.lens.Lens;

/**
 * subclass of abstract class Camera
 */
@Getter
@ToString(callSuper = true)
public class Nikon extends Camera implements CameraBehavioral {

    private final String nikonTechnologyUsed;

    public Nikon(String colour, Boolean hasLivePicture, Lens lens, String nikonTechnologyUsed) {
        super(colour, hasLivePicture, lens);
        this.nikonTechnologyUsed = nikonTechnologyUsed;
        this.name = "Nikon";
    }

    /**
     * Method that prints out to the user the info that the Nikon technology used is coolpix or not.
     */
    private void nikonTechnologyExecution() {
        if(this.nikonTechnologyUsed.equals("coolpix")) {
            System.out.println("coolpix technology in use.");
        } else {
            System.out.println("other technology than coolpix in use.");
        }
    }
}