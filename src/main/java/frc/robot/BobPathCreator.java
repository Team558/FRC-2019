package frc.robot;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import frc.team254.lib.trajectory.WaypointSequence.Waypoint;
import frc.team319.trajectory.AbstractBobPathCreator;
import frc.team319.trajectory.BobPath;
import frc.team319.trajectory.SrxTranslatorConfig;

public class BobPathCreator extends AbstractBobPathCreator {

    private static double robotWidthInFeet = 26.5 / 12.0;
	private static double robotLengthInFeet = 33.5 / 12.0;

	// This point and points like it can be used when there are common starting locatons for the robot
	// Remember that paths should be generated from the center point of the robot
	private static Waypoint startingPoint = new Waypoint(robotLengthInFeet / 2.0, 45.5 / 12.0, 0, 0, 0);
	
	private SrxTranslatorConfig config = new SrxTranslatorConfig();
    
    /*public static void main(String[] args) {
        new BobPathCreator().generatePaths();
	}*/
	
	private BobPathCreator() {
		config.max_acc = 9.0; // Maximum acceleration in FPS
		config.max_vel = 11.0; // Maximum velocity in FPS
		config.wheel_dia_inches = 6.0;
		config.scale_factor = .7; // Used to adjust for a gear ratio and or distance tuning
		config.encoder_ticks_per_rev = 4096; // Count of ticks on your encoder
		config.robotLength = 33.5; // Robot length in inches, used for drawing the robot
		config.robotWidth = 26.5; // Robot width in inches, used for drawing the robot
		config.highGear = false;
	}

    @Override
    protected List<BobPath> getArcs() {
		List<BobPath> paths = new ArrayList<>();
		//paths.addAll(getConfigArcs());
		paths.addAll(generateTeamPaths());
        return paths;
	}

	/**
	 * Use this method to generate team paths. You can create more methods like this one to organize your path, 
	 * just make sure to add the method call to the returned list in getArcs()
	 * @return the list of team paths to generate
	 */
	private List<BobPath> generateTeamPaths() {

		 BobPath flower = new BobPath(config, "Flower");
		 flower.addWaypoint(10, 10, 0, 0, 0);
		 flower.addWaypoint(12, 12, 45, 2, 2);
		 flower.addWaypoint(14, 14, 90, 2, 2);
		 flower.addWaypoint(12, 16, 135, 2, 2);
		 flower.addWaypoint(10, 18, 180, 2, 2);
		 flower.addWaypoint(8, 16, 225, 2, 2);
		 flower.addWaypoint(6, 14, 270, 2, 2);
		 flower.addWaypoint(8, 12, 315, 2, 2);
		 flower.addWaypoint(10, 10, 0, 0, 2);

		 BobPath imageCalibrator = new BobPath(config, "ImageCalibrator");
		 imageCalibrator.addWaypoint(startingPoint);

		 BobPath nate = new BobPath(config, "test");
		 nate.addWaypoint(2, 9.7, 0);
		 nate.addWaypointRelative(10, 0, -10, 0, 7);
		 nate.addWaypointRelative(4, 0, 25, 0, 7);

		 BobPath DriveForwardThreeFeet = new BobPath(config, "DriveForwardThreeFeet", false);
		 DriveForwardThreeFeet.addWaypoint(startingPoint);
		 DriveForwardThreeFeet.addWaypointRelative(3, 0, 0);

		 BobPath TurnScaling = new BobPath(config, "TurnScaling", false);
		 TurnScaling.addWaypoint(startingPoint);
		 TurnScaling.addWaypointRelative(3, 3, 89.99);
		 return asList (TurnScaling); // return asList(path1, path2, path3, ...);*/
	}
	public static void main(String[] args) {
		new BobPathCreator().generatePaths();
	}
}