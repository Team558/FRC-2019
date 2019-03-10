/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class MoveElevatorPositions extends Command {

  double targetPos;

  public MoveElevatorPositions() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevator);
    requires(Robot.cargoDetector);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    boolean iHasCargo = Robot.cargoDetector.readSensor();
    

    if(Robot.m_oi.getPOVElevator() == 180){

      targetPos = Robot.elevator.pickUp;

    }
    else if(Robot.m_oi.getPOVElevator() == 90){

      if(iHasCargo){

        targetPos = Robot.elevator.midCargo;

      }
      else{

        targetPos = Robot.elevator.middleGoal;

      }
    }

    else if(Robot.m_oi.getPOVElevator() == 0){

      if(iHasCargo){

        targetPos = Robot.elevator.topCargo;

      }
      else{

        targetPos = Robot.elevator.highGoal;
      }
    }
    else if(Robot.m_oi.getPOVElevator() == 270){

      targetPos = Robot.elevator.cargoShip;

    }

    Robot.elevator.GoToTarget(targetPos);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
