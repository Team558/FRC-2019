/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;



public class Elevator extends Subsystem {

//Stuff for Nate vvvv ///

  //---Home Position---//
  //Need variable for home position

  //---Hatch Positions---//
  //Need variables for hatch position


  //---Cargo Positions---//
  //Need variables for cargo positions

  //---Soft Limit Positions---//
  //Need variables for top and bottom soft limits


  TalonSRX elevatorLeader = new TalonSRX(4);
  TalonSRX elevatorFollower = new TalonSRX(5);

  //Slots # gains
  public final static int ELEVATOR_UP = 0;
  public final static int ELEVATOR_DOWN = 1;

  public Elevator(){

    elevatorFollower.follow(elevatorLeader);
    elevatorLeader.set(ControlMode.MotionMagic, 0);
    elevatorLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);

    elevatorLeader.configMotionAcceleration(0, 0);
    elevatorLeader.configMotionCruiseVelocity(0, 0);

    // for the elevator going up
    elevatorLeader.selectProfileSlot(ELEVATOR_UP, 0);
    elevatorLeader.config_kF(0, 0, 0);
    elevatorLeader.config_kP(0, 0, 0);
    elevatorLeader.config_kI(0, 0, 0);
    elevatorLeader.config_kD(0, 0, 0);

    //for the elevator going down cause of mass*gravity
    elevatorLeader.selectProfileSlot(ELEVATOR_DOWN, 0);
    elevatorLeader.config_kF(1, 0, 0);
    elevatorLeader.config_kP(1, 0, 0);
    elevatorLeader.config_kI(1, 0, 0);
    elevatorLeader.config_kD(1, 0, 0);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
