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

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {

  TalonSRX leftLeader = new TalonSRX(16);
  TalonSRX rightLeader = new TalonSRX(15);

  TalonSRX leftFollower1 = new TalonSRX(1);
  TalonSRX leftFollower2 = new TalonSRX(2);
  TalonSRX rightFollower1 = new TalonSRX(13);
  TalonSRX rightFollower2 = new TalonSRX(14);

  public Drivetrain(){

    leftFollower1.follow(leftLeader);
    leftFollower2.follow(leftLeader);

    rightFollower1.follow(rightLeader);
    rightFollower2.follow(rightLeader);

  }

  public void drive(double LeftPower, double RightPower){
    leftLeader.set(ControlMode.PercentOutput, LeftPower);
    rightLeader.set(ControlMode.PercentOutput, RightPower);
  }
  public void setRampRate(){
    leftLeader.configOpenloopRamp(.4);
    rightLeader.configOpenloopRamp(.4);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new ElmCityDrive());
  }
}
