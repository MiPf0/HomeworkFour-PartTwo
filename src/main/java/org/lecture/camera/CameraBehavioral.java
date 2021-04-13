package org.lecture.camera;

import org.lecture.motive.Motive;

/**
 * interface for camera behaviours
 */
public interface CameraBehavioral {
    boolean takePicture(Motive m);
    boolean matchingAperture(Motive m);
    void pressTrigger(Motive m);
    void sleep(String timeToSleep);
}