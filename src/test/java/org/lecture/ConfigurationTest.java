package org.lecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lecture.camera.Camera;
import org.lecture.camera.Canon;
import org.lecture.camera.Sony;
import org.lecture.lens.Lens;
import org.lecture.lens.WideAngle;
import org.lecture.motive.Landscape;
import org.lecture.motive.Motive;
import org.lecture.motive.Portrait;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationTest {


    @Test
    public void parse2CameraCompareSize() throws IOException {
        List<Camera> actual = Configuration.getCameras(Paths.get("src/test/resources/config.json"));
        Assertions.assertEquals(2, actual.size());

    }

    @Test
    public void parse2Cameras() throws Exception {
        List<Camera> actual = Configuration.getCameras(Paths.get("src/test/resources/config.json"));

        Camera canon = new Canon("black", true, null, null);
        Camera sony = new Sony("grey", false, null, null);

        List<Camera> expected = new ArrayList<>();
        expected.add(canon);
        expected.add(sony);

        Assertions.assertEquals(expected.get(0), actual.get(0));
        Assertions.assertEquals(expected.get(1), actual.get(1));


    }

    @Test
    public void withoutLenses() throws IOException {
        List<Lens> actual = Configuration.getLenses(Paths.get("src/test/resources/configWithoutLenses.json"));
        Assertions.assertEquals(0, actual.size());

    }

    @Test
    public void parseLens() throws IOException {
        List<Lens> actual = Configuration.getLenses(Paths.get("src/test/resources/config.json"));
        Lens expected = new WideAngle("Zeiss", "f1.8", null);
        Assertions.assertEquals(expected, actual.get(0));

    }


    @Test
    public void parseMotive() throws IOException {
        List<Motive> actual = Configuration.getMotives(Paths.get("src/test/resources/config.json"));
        Motive expected = new Portrait("f2", "0.02", null);
        Assertions.assertEquals(expected, actual.get(0));

    }

    @Test
    public void parseMotive2() throws IOException {
        List<Motive> actual = Configuration.getMotives(Paths.get("src/test/resources/config.json"));
        Motive expected = new Landscape("f1.8", "2", null);
        Assertions.assertEquals(expected, actual.get(1));

    }

}
