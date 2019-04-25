/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.ClimbCommands.ClimbSequence;
import frc.robot.commands.CargoAcutate;
import frc.robot.commands.FireHatchExtender;
import frc.robot.commands.FireHatchGrabber;
import frc.robot.commands.RunElevatorManual;
import frc.robot.commands.VacOnOff;
import frc.robot.commands.ZeroElevator;
import frc.robot.commands.ZeroVac;

public class OI {

    XboxController DriveJoystick = new XboxController(0);
    XboxController operatorStick = new XboxController(1);

	public OI(){

		JoystickButton DuckBillToggle = new JoystickButton(operatorStick, 1);
    DuckBillToggle.toggleWhenPressed(new FireHatchGrabber());
    
    JoystickButton QuackLauncherToggle = new JoystickButton(operatorStick, 2);
		QuackLauncherToggle.toggleWhenPressed(new FireHatchExtender());

		JoystickButton elevatorManualMode = new JoystickButton(operatorStick, 8);
		elevatorManualMode.toggleWhenPressed(new RunElevatorManual());

		JoystickButton zeroElevator = new JoystickButton(operatorStick, 7);
		zeroElevator.whenPressed(new ZeroElevator());
		

		JoystickButton cargoactuate = new JoystickButton(operatorStick, 5);
		cargoactuate.toggleWhenPressed(new CargoAcutate());

		JoystickButton zeroVac = new JoystickButton(DriveJoystick, 7);
		zeroVac.whenPressed(new ZeroVac());

		JoystickButton pumpsOnOff = new JoystickButton(DriveJoystick, 4);
		pumpsOnOff.toggleWhenPressed(new VacOnOff());


		//JoystickButton vacMidPos = new JoystickButton(DriveJoystick, 6);
		//vacMidPos.whenPressed(new VacMidPos());

		JoystickButton climbDeploy = new JoystickButton(DriveJoystick, 5);
		climbDeploy.whenPressed(new ClimbSequence());

  	}
	//Haptic Feedback 
  	public void setrumble(double rumble){
			
		DriveJoystick.setRumble(GenericHID.RumbleType.kLeftRumble, rumble);
		DriveJoystick.setRumble(GenericHID.RumbleType.kRightRumble, rumble);
	
	}

	//Elm City Drive OI Methods
	public boolean GetQuickTurn(){
		return DriveJoystick.getRawButton(RobotMap.quickTurnButton);
		
	}
	public double GetThrottle(){
		double reverse = DriveJoystick.getRawAxis(RobotMap.throttleForwardAxis);
	   	double forward = DriveJoystick.getRawAxis(RobotMap.throttleReverseAxis);

		
		if ((reverse > .1) && (forward >.1)){
			return 0;
		}
		else if (forward > .1){
			return forward;
		}
		else if (reverse > .1){
			return -reverse;
		}
		else
			return 0;
	}
	public void limelightRumble(){

		if(Robot.limeLight.isTarget() == 1 || Robot.transducer.getAveragePressure() <= -10){

			Robot.m_oi.setrumble(.6);
	  
		  }
		  else{
	  
			Robot.m_oi.setrumble(0);
	  
		  }

	}

	
	public double GetTurn(){
		return -DriveJoystick.getRawAxis(RobotMap.turnAxis);
	}

	public double getPOVElevator(){

		return operatorStick.getPOV();

	}

	public double GetClimberAxis(){
		return -DriveJoystick.getRawAxis(5);
	}
	public double GetElevatorAxis(){
		return operatorStick.getRawAxis(1);
	}

	public boolean vacuumClimberOverride(){
		return DriveJoystick.getRawButton(6);
	}


	public double GetCargoThrottle(){
		double reverse = operatorStick.getRawAxis(3);
	   	double forward = operatorStick.getRawAxis(2);
		if ((reverse > .1) && (forward >.1)){
			return 0;
		}
		else if (forward > .1){
			return forward;
		}
		else if (reverse > .1){
			return -reverse;
		}
		else
			return 0;
	}
	public boolean getMidPosition(){

		return DriveJoystick.getRawButton(6);
	}
	public boolean GetPixyDrive(){
		return DriveJoystick.getRawButton(1);
	}
	public boolean getLowPosition(){

		return DriveJoystick.getRawButton(5);

	}
	public boolean limeLightAutoDrive(){

		return DriveJoystick.getRawButton(2);

	}
	public boolean limeLightCargo(){

		return DriveJoystick.getRawButton(4);

	}
	public boolean limeLightCargoAuto(){

		return DriveJoystick.getRawButton(3);

	}
}
