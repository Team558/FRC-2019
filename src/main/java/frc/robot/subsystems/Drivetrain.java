/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ElmCityDrive;



public class Drivetrain extends Subsystem {

  TalonSRX leftLeader = new TalonSRX(15);
  TalonSRX rightLeader = new TalonSRX(2);

  VictorSPX leftFollower1 = new VictorSPX(14);
  VictorSPX leftFollower2 = new VictorSPX(13);
  VictorSPX rightFollower1 = new VictorSPX(1);
  VictorSPX rightFollower2 = new VictorSPX(16);

  public Drivetrain(){

    leftFollower1.follow(leftLeader);
    leftFollower2.follow(leftLeader);

    rightFollower1.follow(rightLeader);
    rightFollower2.follow(rightLeader);

    rightLeader.setInverted(true);
    rightFollower1.setInverted(true);
    rightFollower2.setInverted(true);


    leftLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    rightLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

  } 

  public void drive(double leftPower, double rightPower){
    leftLeader.set(ControlMode.PercentOutput, leftPower);
    rightLeader.set(ControlMode.PercentOutput, rightPower);
  }
  
  public void setRampRate(){
    leftLeader.configOpenloopRamp(.4);
    rightLeader.configOpenloopRamp(.4);
  }
  public double readLeftEncoder(){

    return leftLeader.getSelectedSensorPosition();

  }

  public double readRightEncoder(){

    return rightLeader.getSelectedSensorPosition();

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new ElmCityDrive());
  }
}
