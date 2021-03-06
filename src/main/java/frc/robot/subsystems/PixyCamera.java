
package frc.robot.subsystems;



import frc.robot.pixy2api.Pixy2;
import frc.robot.pixy2api.Pixy2CCC;
import frc.robot.pixy2api.links.*;

public class PixyCamera {

	private final Pixy2 pixy;
	private boolean laststate = false;
	public final static int PixyResult = 0;

	public PixyCamera(Link link) {
		pixy = Pixy2.createInstance(link);
		pixy.init();
	}

	public PixyCamera(Link link, int arg) {
		pixy = Pixy2.createInstance(link);
		pixy.init(arg);
	}

	public void run() {

		final int pixystatus = pixy.init(PixyResult); // Checks for Pixy2 Error (If issue, Robot is Not Effected)
		if (pixystatus == 0) {

			final int count = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 2);

			TargetLargest.run(count); // Track Target Code Run

		}

		
	}

	public void light(boolean state) {
		final int pixystatus = pixy.init(PixyResult); // Checks for Pixy2 Error (If issue, Robot is Not Effected)
		if (pixystatus == 0) {

			if ((state == true) && (laststate == false)) { // Pixy2 Lamp Turns On/Off w/ Shuffleboard Button
				laststate = true;
				pixy.setLamp((byte) 1, (byte) 0);
			} else if (state == false && laststate == true) {
				laststate = false;
				pixy.setLamp((byte) 0, (byte) 0);
			}

		}
	}

	public Pixy2 getPixy() {
		return pixy;
	}

}