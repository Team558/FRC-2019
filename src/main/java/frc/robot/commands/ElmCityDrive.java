/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
//import frc.robot.subsystems.TargetLargest;
import frc.robot.util.Util;

public class ElmCityDrive extends Command {
  private double oldWheel = 0.0;
  private double quickStopAccumulator;
  private double wheelDeadband = 0.1;
  private double throttleDeadband = 0.1;
  private double pixyWheelDeadband = .01;

  public ElmCityDrive() {
      // Use requires() here to declare subsystem dependencies
      requires(Robot.drivetrain);
      
  }

  // Called just before this Command runs the first time
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    
    double wheelNonLinearity;
    boolean isPixyDrive = Robot.m_oi.GetPixyDrive();
    boolean isPixy2Drive = Robot.m_oi.pixy2LineDrive();
    double wheel;
    
  //else{

    wheel = handleDeadband(Robot.m_oi.GetTurn(), wheelDeadband);

  //}
 
   


      double throttle = handleDeadband(Robot.m_oi.GetThrottle(), throttleDeadband);

      double negInertia = wheel - oldWheel;
      oldWheel = wheel;

      wheelNonLinearity = 0.5;
      // Apply a sin function that's scaled to make it feel better.
      wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
          Math.sin(Math.PI / 2.0 * wheelNonLinearity);
      wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
          Math.sin(Math.PI / 2.0 * wheelNonLinearity);
      wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
          Math.sin(Math.PI / 2.0 * wheelNonLinearity);
      

      double leftPwm, rightPwm, overPower;
      double sensitivity = RobotMap.normalTurnSensitivity;

      double angularPower;
      double linearPower;

      // Negative inertia!
      double negInertiaAccumulator = 0.0;
      double negInertiaScalar;
        if (wheel * negInertia > 0) {
          negInertiaScalar = 2.5;
        } 
        else {
          if (Math.abs(wheel) > 0.65) {
            negInertiaScalar = 5.0;
          } 
          else {
            negInertiaScalar = 3.0;
          }
        }
        sensitivity = RobotMap.normalTurnSensitivity;

        if (Math.abs(throttle) > 0.1) {
        }
      
      double negInertiaPower = negInertia * negInertiaScalar;
      negInertiaAccumulator += negInertiaPower;

      wheel = wheel + negInertiaAccumulator;
      if (negInertiaAccumulator > 1) {
        negInertiaAccumulator -= 1;
      }
      else if (negInertiaAccumulator < -1) {
        negInertiaAccumulator += 1;
      } 
      else {
        negInertiaAccumulator = 0;
      }
      linearPower = throttle;

      // Quickturn!
      //if (isQuickTurn) {
        if (Math.abs(linearPower) < 0.2) {
          double alpha = 0.1;
          quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha *
              Util.limit(wheel, 1.0) * 5;
        //}
        overPower = 1.0;
        sensitivity = 1.0;
        
        angularPower = RobotMap.quickturnSensitivity * wheel;
      } 
      else {
        overPower = 0.0;
        angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
        if (quickStopAccumulator > 1) {
          quickStopAccumulator -= 1;
        } else if (quickStopAccumulator < -1) {
          quickStopAccumulator += 1;
        } else {
          quickStopAccumulator = 0.0;
        }
      }

      rightPwm = leftPwm = linearPower;
      leftPwm += angularPower;
      rightPwm -= angularPower;

      if (leftPwm > 1.0) {
        rightPwm -= overPower * (leftPwm - 1.0);
        leftPwm = 1.0;
      } else if (rightPwm > 1.0) {
        leftPwm -= overPower * (rightPwm - 1.0);
        rightPwm = 1.0;
      } else if (leftPwm < -1.0) {
        rightPwm += overPower * (-1.0 - leftPwm);
        leftPwm = -1.0;
      } else if (rightPwm < -1.0) {
        leftPwm += overPower * (-1.0 - rightPwm);
        rightPwm = -1.0;
      }

     if (Math.abs(linearPower) < 0.05) {
        
      Robot.drivetrain.drive(leftPwm+.01, rightPwm+.01);
     }
     else{
      Robot.drivetrain.drive(leftPwm, rightPwm);

     }

  }
    
  public double handleDeadband(double val, double deadband) {
      if(val > -deadband && val < deadband){

        return 0;

      }
      else if(val > 0){

        double scaledInput = (val - deadband)/(1 - deadband);
        return scaledInput;

      }

      else{

        double scaledInput = (val + deadband)/(1 -  deadband);
        return scaledInput;

      }
    }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
      return false;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
