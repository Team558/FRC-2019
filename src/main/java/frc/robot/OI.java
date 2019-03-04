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
				QuackLauncherToggle.toggleWhenPressed(new HatchExtendPinch());
		
		JoystickButton elevatorPickup = new JoystickButton(operatorStick, 3);
		elevatorPickup.whenPressed(new ElevatorToPickup());
		JoystickButton elevatorMidGoal = new JoystickButton(operatorStick, 4);
		elevatorMidGoal.whenPressed(new ElevatorToMid());
		JoystickButton elevatorManualMode = new JoystickButton(operatorStick, 8);
		elevatorManualMode.whenPressed(new RunElevatorManual());
		JoystickButton zeroElevator = new JoystickButton(operatorStick, 7);
		zeroElevator.whenPressed(new ZeroElevator());
		

		JoystickButton cargoactuate = new JoystickButton(operatorStick, 5);
		cargoactuate.toggleWhenPressed(new CargoAcutate());
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

			
		public double GetTurn(){
			return -DriveJoystick.getRawAxis(RobotMap.turnAxis);
		}
		
		public double GetClimberAxis(){
			return operatorStick.getRawAxis(1);
		}
		public double GetElevatorAxis(){
			return operatorStick.getRawAxis(5);
		}
		

		public double GetCargoThrottle(){
			double reverse = operatorStick.getRawAxis(RobotMap.throttleForwardAxis);
		   	double forward = operatorStick.getRawAxis(RobotMap.throttleReverseAxis);

		    	
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


}
