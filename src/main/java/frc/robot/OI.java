/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

public class OI {

    XboxController DriveJoystick = new XboxController(0);
    XboxController operatorStick = new XboxController(1);

    public double leftSideJoystick(){
        return DriveJoystick.getRawAxis(1);
    } 
    public double rightSideJoystick(){
        return DriveJoystick.getRawAxis(5);
    }
    public OI(){
        JoystickButton DuckBillToggle = new JoystickButton(DriveJoystick, 1);
        DuckBillToggle.toggleWhenPressed(new FireHatchGrabber());
    
        JoystickButton QuackLauncherToggle = new JoystickButton(DriveJoystick, 2);
        QuackLauncherToggle.toggleWhenPressed(new FireHatchExtender());
    }
    
    //Elm City Drive OI Methods
		public boolean GetQuickTurn(){
			return DriveJoystick.getRawButton(RobotMap.quickTurnButton);
				
		}
		public double GetThrottle(){
			double reverse = DriveJoystick.getRawAxis(RobotMap.throttleForwardAxis);
		   	double forward = DriveJoystick.getRawAxis(RobotMap.throttleReverseAxis);

	//***This might be a redundant deadband or the elm city command might be**//
		    	
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
			return DriveJoystick.getRawAxis(RobotMap.turnAxis);
		}
		
		public double GetGripperAxis(){
			return operatorStick.getRawAxis(2) + operatorStick.getRawAxis(3);
		}
}
