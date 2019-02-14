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

    public double leftSideJoystick(){
        return DriveJoystick.getRawAxis(1);
    } 
    public double rightSideJoystick(){
        return DriveJoystick.getRawAxis(5);
    }
    public OI(){
        JoystickButton A = new JoystickButton(DriveJoystick, 1);
        A.toggleWhenPressed(new FireHatchGrabber());
    
        JoystickButton B = new JoystickButton(DriveJoystick, 2);
        B.toggleWhenPressed(new FireHatchExtender());
    }
    


    

}
