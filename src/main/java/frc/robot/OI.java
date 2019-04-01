/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

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

		JoystickButton climbBackButton = new JoystickButton(DriveJoystick, 2);
		//climbBackButton.whileHeld(new LiftButt());

		JoystickButton climberButton = new JoystickButton(DriveJoystick, 3);
		//climberButton.whileHeld(new WheeliBarDownAndUp());

  }
    
		//Elm City Drive OI Methods
		public double GetTurn(){
			return -DriveJoystick.getRawAxis(RobotMap.turnAxis);
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

		public double getPOVElevator(){

			return operatorStick.getPOV();

		}
		
		/*public double GetClimberAxis(){
			return operatorStick.getRawAxis(5);
		}*/
		public double GetElevatorAxis(){
			return operatorStick.getRawAxis(1);
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

			public boolean GetPixyDrive(){
				return DriveJoystick.getRawButton(1);
			}
}
