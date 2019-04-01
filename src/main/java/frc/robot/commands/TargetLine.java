package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.pixy2api.*;
import frc.robot.subsystems.*;
import frc.robot.pixy2api.Pixy2Line;
import frc.robot.pixy2api.Pixy2Line.Vector;

public class TargetLine extends Command {

	private static TargetLine instance;

	
	private Pixy2 pixy;

	
	public static TargetLine getInstance() {
		if (instance == null)
			instance = new TargetLine();
		return instance;
	}

	public TargetLine() {
		requires(Robot.pixy2Handler);
	}

	@Override
	protected void initialize() {
		pixy = Camera.getPixyCamera().getPixy();
		pixy.getLine().setMode(Pixy2Line.LINE_MODE_WHITE_LINE);
		pixy.setLamp((byte) 0, (byte) 0);
		pixy.setLED(0, 0, 0);
		//frameMid = pixy.getFrameHeight() / 2;
	}

	@Override
	protected void execute() {
		pixy.getLine().getMainFeatures();
		Vector[] vectors = pixy.getLine().getVectors();
		double testAngle = 0.0;
		double testLength = 0.0;
		int testPosition = 0;
		if (vectors != null) {
			for (Vector v : vectors) {
				Robot.pixy2Handler.setVectorX0(v.getX0());
				Robot.pixy2Handler.setVectorX1(v.getX1());
				Robot.pixy2Handler.setVectorY0(v.getY0());
				Robot.pixy2Handler.setVectorY1(v.getY1());
			}
		}
	}

	
	@Override
	protected boolean isFinished() {
		return false;
	}

}