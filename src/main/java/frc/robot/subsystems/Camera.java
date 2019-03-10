package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.pixy2api.links.*;

public class Camera {

    private static PixyCamera pixy = null;

    public static void setup() {
        pixy = new PixyCamera(new SPILink());

    }

    public static void light(boolean state) {
        pixy.light(state);
    }

    public static void run() {
        pixy.run();
    }

    public static PixyCamera getPixyCamera() {
        return pixy;
    }

 

}