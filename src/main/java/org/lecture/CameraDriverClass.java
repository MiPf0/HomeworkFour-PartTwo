package org.lecture;


import lombok.extern.slf4j.Slf4j;
import org.lecture.camera.Camera;
import org.lecture.camera.Canon;
import org.lecture.camera.Nikon;
import org.lecture.camera.Sony;
import org.lecture.lens.Lens;
import org.lecture.motive.Motive;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class that lets you select camera, lens and motives and simulate taking a picture.
 * It gets the available objects from a config.json file.
 */
@Slf4j
public class CameraDriverClass {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/config.json");

        //Load config
        List<Camera> cameras = Configuration.getCameras(path);
        List<Lens> lenses = Configuration.getLenses(path);
        List<Motive> motives = Configuration.getMotives(path);
        log.info("configuration successfully loaded.");

        Lens lens = null;
        Camera camera = null;
        Motive motive = null;

        do {
            int option = getOption();
            switch (option) {
                case 1 -> camera = chooseCamera(cameras);
                case 2 -> {

                    Lens newLens = chooseLens(lenses);
                    /*
                    if it is a new lens, mount it on a selected camera
                    (otherwise, only the lens is stored for later mounting.
                     */
                    if (newLens != lens && camera != null) {
                        //change the lens (camera is immutable!)
                        if (camera instanceof Canon) {
                            camera = new Canon(camera.getName(), camera.getLivePicture(), lens, ((Canon) camera).getHasCanonConnectApp());
                        } else if (camera instanceof Nikon) {
                            camera = new Nikon(camera.getName(), camera.getLivePicture(), lens, ((Nikon) camera).getNikonTechnologyUsed());
                        } else if (camera instanceof Sony) {
                            camera = new Sony(camera.getName(), camera.getLivePicture(), lens, ((Sony) camera).getSonyAlphaVersion());
                        }
                        System.out.println("Changed lens");
                        log.info("Lens successfully changed.");
                    }
                    System.out.println("Lens chosen");
                    log.info("Lens successfully chosen.");
                    lens = newLens;  //store it for later mounting
                    System.out.println();
                }
                case 3 -> motive = chooseMotive(motives);
                case 4 -> {
                    takePicture(lens, camera, motive);
                    System.out.println();
                    log.info("Picture successfully taken.");
                }
                default -> {
                    System.exit(0);
                    log.error("User Input out of option range - therefore exiting the program.");
                }
            }
        } while (true);

    }

    /**
     * Takes a picture with the configred parts.
     * if one of them is not available, it exits the method.
     * @param lens the chosen lens.
     * @param camera the chosen camera.
     * @param motive the chosen motive
     */
    private static void takePicture(Lens lens, Camera camera, Motive motive) {
        if (camera == null) {
            System.out.println("No camera chosen");
        } else if (lens == null) {
            System.out.println("No lens chosen");
        } else if (motive == null) {
            System.out.println("No motive chosen");
        } else {

            System.out.println("Take pic");
            log.info("Picture taking process successfully started.");
            if (camera.getLens() == null) {
                if (camera instanceof Canon) {
                    camera = new Canon(camera.getName(), camera.getLivePicture(), lens, ((Canon) camera).getHasCanonConnectApp());
                } else if (camera instanceof Nikon) {
                    camera = new Nikon(camera.getName(), camera.getLivePicture(), lens, ((Nikon) camera).getNikonTechnologyUsed());
                } else if (camera instanceof Sony) {
                    camera = new Sony(camera.getName(), camera.getLivePicture(), lens, ((Sony) camera).getSonyAlphaVersion());
                }
            }
            var tookPicture = camera.takePicture(motive);
            if (!tookPicture) {
                System.out.println("Exiting...");
                System.exit(0);
                log.info("System exited.");
            }
        }
    }

    /**
     * configures the available options for the user.
     *
     * @return The selected option
     */
    private static int getOption() {
        String output = """
                1 - Choose Camera
                2 - Choose Lens
                3 - Choose Motive
                4 - Take picture
                Every other input - End program
                """;
        System.out.println(output);
        int option;
        try {
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            option = -1;  //set to a non-used option. application will be stopped afterwards.
        }
        log.info("Option successfully chosen.");
        return option;
    }

    /**
     * Prints the available camera on the console
     * and selects the camera according the the input
     * If an invalid input happens, the program ends.
     * It also ends, if no camera is configured at all.
     * @param cameras the configured cameras
     * @return the chosen camera
     */
    private static Camera chooseCamera(List<Camera> cameras) {
        System.out.println("Available Cameras");
        int chosenCamera = -1;
        if (!cameras.isEmpty()) {
            //prints to the console with an index starting at 1
            for (int i = 0; i < cameras.size(); i++) {
                System.out.println((i + 1) + " - " + cameras.get(i).getName());
            }
            Scanner scanner = new Scanner(System.in);
            chosenCamera = scanner.nextInt();
            scanner.nextLine();

            if (chosenCamera < 1 || chosenCamera > cameras.size()) {
                System.out.println("Non existing camera chosen");
                log.error("No existing camera chosen, therefore exit.");
                System.exit(-2);
            }
        } else {
            if (chosenCamera == -1) {
                System.out.println("No configured cameras available. Exiting...");
                log.error("No configured cameras available, therefore exit.");
                System.exit(-1);
            }
        }
        System.out.println("Camera chosen");
        System.out.println();
        log.info("Camera successfully chosen.");
        return cameras.get(chosenCamera - 1);

    }

    /**
     * Prints the available camera on the console
     * and selects the lens according the the input
     * If an invalid input happens, the program ends.
     * It also ends, if no lenses is configured at all.
     * @param lenses the configured lenses
     * @return the chosen lens
     */
    private static Lens chooseLens(List<Lens> lenses) {
        System.out.println("Available Lenses");
        int chosenLens = -1;
        if (!lenses.isEmpty()) {
            //prints to the console with an index starting at 1
            for (int i = 0; i < lenses.size(); i++) {
                System.out.println((i + 1) + " - " + lenses.get(i).getAperture() + " by " + lenses.get(i).getManufacturer());
            }
            Scanner scanner = new Scanner(System.in);
            chosenLens = scanner.nextInt();
            scanner.nextLine();
            if (chosenLens < 1 || chosenLens > lenses.size()) {
                System.out.println("Non existing lens chosen");
                System.exit(-2);
            } else {
                if (chosenLens == -1) {
                    System.out.println("No configured lenses available. Exiting...");
                    System.exit(-1);
                }
            }
        }
        log.info("Lens successfully chosen.");
        return lenses.get(chosenLens - 1);
    }

    /**
     * Prints the available camera on the console
     * and selects the motive according the the input
     * If an invalid input happens, the program ends.
     * It also ends, if no motives is configured at all.
     * @param motives the configured motives
     * @return the chosen motive
     */
    private static Motive chooseMotive(List<Motive> motives) {
        System.out.println("Available motives");
        int chosenMotive = -1;
        if (!motives.isEmpty()) {
            //prints to the console with an index starting at 1
            for (int i = 0; i < motives.size(); i++) {
                System.out.println((i + 1) + " - " + motives.get(i).getOptimalAperture() + " for " + motives.get(i).getType());
            }
            Scanner scanner = new Scanner(System.in);
            chosenMotive = scanner.nextInt();
            scanner.nextLine();
            if (chosenMotive < 1 || chosenMotive > motives.size()) {
                System.out.println("Non existing motive chosen");
                System.exit(-2);
            }
        } else {
            if (chosenMotive == -1) {
                System.out.println("No configured motives available. Exiting...");
                System.exit(-1);
            }
        }
        System.out.println("Motive chosen");
        System.out.println();
        log.info("Motive successfully chosen.");
        return motives.get(chosenMotive - 1);
    }
}