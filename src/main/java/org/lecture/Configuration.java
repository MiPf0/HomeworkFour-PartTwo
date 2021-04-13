package org.lecture;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.lecture.camera.Camera;
import org.lecture.camera.Canon;
import org.lecture.camera.Nikon;
import org.lecture.camera.Sony;
import org.lecture.lens.Lens;
import org.lecture.lens.Macro;
import org.lecture.lens.Standard;
import org.lecture.lens.WideAngle;
import org.lecture.motive.Landscape;
import org.lecture.motive.Motive;
import org.lecture.motive.Night;
import org.lecture.motive.Portrait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that reads the configuration from a given path to a json file.
 */
@Slf4j
public class Configuration {

    /**
     * Reads the configured cameras from a json config file
     *
     * @param p the path where the file is located
     * @return all the configured cameras
     * @throws IOException
     */
    public static List<Camera> getCameras(Path p) throws IOException {
        List<Camera> cameras = new ArrayList<>();
        if (Files.exists(p)) {
            var content = new String(Files.readAllBytes(p));
            JSONObject jo = new JSONObject(content);
            for (String key : jo.keySet()) {
                if (key.equals("cameras")) {
                    for (String subKey : jo.getJSONObject(key).keySet()) {
                        cameras.add(createCamera(jo.getJSONObject(key), subKey));

                    }
                }
            }
        }

        return cameras;
    }


    /**
     * REads the configured lenses from a json config file
     *
     * @param p the path where the file is located
     * @return all configured lenses
     * @throws IOException
     */
    public static List<Lens> getLenses(Path p) throws IOException {
        List<Lens> lenses = new ArrayList<>();

        if (Files.exists(p)) {
            var content = new String(Files.readAllBytes(p));
            JSONObject jo = new JSONObject(content);
            for (String key : jo.keySet()) {
                if (key.equals("lenses")) {
                    for (String subKey : jo.getJSONObject(key).keySet()) {
                        lenses.add(createLens(jo.getJSONObject(key), subKey));

                    }
                }
            }
        }
        return lenses;
    }

    /**
     * Reads the configured motives from a json config file
     *
     * @param p the path where the file is located
     * @return all configured motives
     * @throws IOException
     */
    public static List<Motive> getMotives(Path p) throws IOException {
        List<Motive> motives = new ArrayList<>();
        if (Files.exists(p)) {
            var content = new String(Files.readAllBytes(p));
            JSONObject jo = new JSONObject(content);
            for (String key : jo.keySet()) {
                if (key.equals("motives")) {
                    for (String subKey : jo.getJSONObject(key).keySet()) {
                        //System.out.println(jo.getString(subKey));
                        motives.add(createMotive(jo.getJSONObject(key), subKey));
                    }
                }
            }
        }
        return motives;
    }

    /**
     * converts a json object into a motive
     *
     * @param jo     the json object to convert
     * @param subKey which key to convert
     * @return the converted motive
     */
    private static Motive createMotive(JSONObject jo, String subKey) {
        var aperture = jo.getJSONObject(subKey).getString("aperture");
        var type = jo.getJSONObject(subKey).getString("type");
        var exposure = jo.getJSONObject(subKey).getString("exposure");
        if(type.contains("Landscape")) {
            return new Landscape(aperture, exposure, null);
        } else if(type.contains("Night")) {
            return new Night(aperture, exposure, null);
        } else if(type.contains("Portrait")) {
            return new Portrait(aperture, exposure, null);
        } else {
            throw new IllegalArgumentException("Unrecognized camera");
        }

    }

    /**
     * converts a json object into a camera
     *
     * @param jo     the json object to convert
     * @param subKey which key to convert
     * @return the converted camera
     */
    private static Camera createCamera(JSONObject jo, String subKey) {
        var colour = jo.getJSONObject(subKey).getString("colour");
        var livePic = jo.getJSONObject(subKey).getString("hasLivePicture");
        var name = jo.getJSONObject(subKey).getString("name");
        if(name.contains("Canon")) {
            return new Canon(colour, Boolean.parseBoolean(livePic), null, null);
        } else if(name.contains("Nikon")) {
            return new Nikon(colour, Boolean.parseBoolean(livePic), null, null);
        } else if(name.contains("Sony")) {
            return new Sony(colour, Boolean.parseBoolean(livePic), null, null);
        } else {
            throw new IllegalArgumentException("Unrecognized camera");
        }
    }

    /**
     * converts a json object into a lens
     *
     * @param jo     the json object to convert
     * @param subKey which key to convert
     * @return the converted lens
     */
    private static Lens createLens(JSONObject jo, String subKey) {
        var aperture = jo.getJSONObject(subKey).getString("aperture");
        var apertureOnlyNumber = aperture.substring(1);

        Double i = null;
        try {
            i = Double.parseDouble(apertureOnlyNumber);
        } catch (IllegalArgumentException e) {
            log.error("Unrecognized aperture input");
        }

        var manufacturer = jo.getJSONObject(subKey).getString("manufacturer");

        if(i<2) {
            return new WideAngle(manufacturer, aperture, null);
        } else if (i >= 6) {
            return new Macro(manufacturer, aperture, null);
        } else {
            return new Standard(manufacturer, aperture, null);
        }
    }
}