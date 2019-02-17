/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;



public class Elevator extends Subsystem {

  TalonSRX elevatorLeader = new TalonSRX(4);
  TalonSRX elevatorFollower = new TalonSRX(5);

  //Slots # gains
  public final static int ELEVATOR_UP = 0;
  public final static int ELEVATOR_DOWN = 1;

  public Elevator(){

    elevatorFollower.follow(elevatorLeader);


  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
