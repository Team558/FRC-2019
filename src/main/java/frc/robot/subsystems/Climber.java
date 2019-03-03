 /*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.RunClimberManual;



public class Climber extends Subsystem {

  TalonSRX climberLeader = new TalonSRX(3);
  TalonSRX climberFollower = new TalonSRX(12);

public Climber(){
  climberFollower.follow(climberLeader);
}
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new RunClimberManual());
  }

  public void DriveClimber(double output){
    climberLeader.set(ControlMode.PercentOutput, output);
  }

}
