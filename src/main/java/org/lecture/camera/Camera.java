package org.lecture.camera;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.lecture.lens.Lens;
import org.lecture.motive.Motive;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * The representation of a Camera = abstract class and not instantiable.
 * It is responsible for its parameters
 * and the lens, which it uses to take a picture of a given motive.
 */
@Slf4j
@Getter
@EqualsAndHashCode
public abstract class Camera implements CameraBehavioral {

    protected String name;
    protected final String colour;
    protected Boolean livePicture;

    @EqualsAndHashCode.Exclude
    protected final Lens lens;

    /**
     * constructor method
     * @param name           name of the producer
     * @param colour         colour of the body
     * @param hasLivePicture is live picture (otherwise an optical viewfinder)
     * @param lens           the Lens mounted on the camera
     */
    protected Camera(String name, String colour, Boolean hasLivePicture, Lens lens) {
        this.name = name;
        this.colour = colour;
        livePicture = hasLivePicture;
        this.lens = lens;
    }

    /**
     * method overloading: 2nd constructor for child classes to be able to set their types individually
     * @param colour         colour of the body
     * @param hasLivePicture is live picture (otherwise an optical viewfinder)
     * @param lens           the Lens mounted on the camera
     */
    protected Camera(String colour, Boolean hasLivePicture, Lens lens) {
        this.colour = colour;
        livePicture = hasLivePicture;
        this.lens = lens;
    }

    /**
     * Simulates taking a picture
     *
     * @param m the Motive to photograph
     * @return false in case the user does not want to take the picture or the is not satisfied with the aperature
     */
    @Override
    public boolean takePicture(Motive m) {
        Scanner scanner = new Scanner(System.in);

        if (livePicture) {
            System.out.println("Checking live picture...");
        } else {
            System.out.println("Checking optical viewfinder ...");
        }
        System.out.println("Continue to take a picture? y/n");
        var takePic = scanner.nextLine();
        if (!takePic.equalsIgnoreCase("y")) {
            return false;
        }


        if (!matchingAperture(m)) {
            System.out.println("Aperture not matching, continue? y/n");
            log.info("Aperture does not match - but taking picture still possible.");
            var input = scanner.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                return false;
            }
        }
        pressTrigger(m);
        return true;
    }

    /**
     * Checks if the aperture matches the configured optimal aperture
     *
     * @param m the motive to compare with
     * @return true if matching. false otherwise
     */
    @Override
    public boolean matchingAperture(Motive m) {
        return lens.getAperture().equals(m.getOptimalAperture());
    }

    /**
     * Simulates to process of pressing the trigger.
     * Outputs the configuration to the console.
     *
     * @param m the motive to photograph
     */
    @Override
    public void pressTrigger(Motive m) {
        System.out.println("Taking picture " + m.getType() + " for " + m.getExposure() + " seconds with " + this.name);
        System.out.println("Used lense: " + this.getLens().getAperture() + " by " + this.getLens().getManufacturer());
       //OPTIONAL!
        sleep(m.getExposure());
        //output of quality of picture
        if (matchingAperture(m)) {
            System.out.println(m.getType() + " taken. Looks wonderful");
        } else {
            System.out.println(m.getType() + " taken. Looks a bit... odd");
        }
    }

    /**
     * OPTIONAL METHOD for Homework Assignment (not part of the assignment text)
     * Pauses the application for the configured amount of seconds
     * @param timeToSleep the time in seconds
     */
    @Override
    public void sleep(String timeToSleep)  {
        System.out.println("Waiting until picture is taken");
        //convert decimal separator from comma to dot
        if (timeToSleep.contains(",")) {
            timeToSleep = timeToSleep.replace(",", ".");
        }

        double time = Double.parseDouble(timeToSleep);
        String milli = Double.toString(time * 1000);  //convert seconds to milliseconds
        //remove perhaps still existing decimal places
        if (milli.contains(".")) {
            milli = milli.substring(0, milli.indexOf("."));
        }
        try {
            //wait the configured amount of time
            TimeUnit.MILLISECONDS.sleep(Integer.parseInt(milli));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}