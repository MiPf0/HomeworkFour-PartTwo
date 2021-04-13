package org.lecture.camera;

import lombok.Getter;
import lombok.ToString;
import org.lecture.lens.Lens;

/**
 * subclass of abstract class Camera
 */
@Getter
@ToString(callSuper = true)
public class Canon extends Camera implements CameraBehavioral {

    private final Boolean hasCanonConnectApp;

    public Canon(String colour, Boolean hasLivePicture, Lens lens, Boolean hasCanonConnectApp) {
        super(colour, hasLivePicture, lens);
        this.hasCanonConnectApp = hasCanonConnectApp;
        this.name = "Canon";
    }

    /**
     * Method that prints out to the user the info that camera has no Canon Connect App.
     */
    private void appConnectionWarning() {
        if(!this.hasCanonConnectApp) {
            System.out.println("Camera has no Canon connect app.");
        }
    }
}