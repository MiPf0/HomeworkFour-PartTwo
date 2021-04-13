package org.lecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lecture.camera.Camera;
import org.lecture.camera.Canon;
import org.lecture.lens.Lens;
import org.lecture.lens.WideAngle;
import org.lecture.motive.Landscape;
import org.lecture.motive.Motive;

import java.io.ByteArrayInputStream;

class CameraTest {

    @Test
    public void takePicture() {
        Lens zeiss = new WideAngle("Zeiss", "f1.8", null);
        Camera canon = new Canon("black", true, zeiss, null);
        Motive motive = new Landscape("f2", "Portrait", null);

        //simulate input
        String input = "y"
                + "\n"
                + "y"
                + "\n"
                + "y"
                + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));  //mock System.in
        boolean actual = canon.takePicture(motive);

        Assertions.assertTrue(actual);

    }

    @Test
    public void pictureStopAtViewfinder() {
        Lens zeiss = new WideAngle("Zeiss", "f1.8", null);
        Camera canon = new Canon("black", true, zeiss, null);
        Motive motive = new Landscape("f2", "Portrait", null);

        //simulate input
        String input = "y"
                + "\n"
                + "n"
                + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));  //mock System.in
        boolean actual = canon.takePicture(motive);

        Assertions.assertFalse(actual);

    }

    @Test
    public void pictureStopAtAperature() {
        Lens zeiss = new WideAngle("Zeiss", "f1.8", null);
        Camera canon = new Canon("black", true, zeiss, null);
        Motive motive = new Landscape("f2", "Portrait", null);

        //simulate input
        String input = "n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));  //mock System.in
        boolean actual = canon.takePicture(motive);

        Assertions.assertFalse(actual);

    }
}