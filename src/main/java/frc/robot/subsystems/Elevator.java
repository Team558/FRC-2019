/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.MoveElevatorPositions;

public class Elevator extends Subsystem {
 
  TalonSRX elevatorLeader = new TalonSRX(6);
  TalonSRX elevatorFollower = new TalonSRX(7);

  public int elevatorZero = 0;
  public int pickUp = 200;
  public int middleGoal = 10230;
  public int highGoal = 20612;
  public int topLimit = 26100;
  public int cargoShip = 5000;
  public int midCargo = 9340;
  public int topCargo = 19400;

  //Slots # gains
  public final static int ELEVATOR_UP = 0;
  public final static int ELEVATOR_DOWN = 1;

  public Elevator(){
    elevatorLeader.setInverted(true);
    elevatorFollower.setInverted(true);
    elevatorFollower.follow(elevatorLeader);

    elevatorLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    elevatorLeader.configForwardSoftLimitEnable(false);
    elevatorLeader.configReverseSoftLimitEnable(false);

    elevatorLeader.configForwardLimitSwitchSource(RemoteLimitSwitchSource.RemoteTalonSRX,LimitSwitchNormal.NormallyOpen, 7, 10);
    elevatorLeader.configReverseLimitSwitchSource(RemoteLimitSwitchSource.RemoteTalonSRX,LimitSwitchNormal.NormallyOpen, 7, 10);

    elevatorLeader.setSensorPhase(false);

    // for the elevator going up
    elevatorLeader.config_kF(0, .3197);
    elevatorLeader.config_kP(0, 0.1926);
    elevatorLeader.config_kI(0, .001);
    elevatorLeader.config_kD(0, 13);
    elevatorLeader.config_IntegralZone(0, 500);

    //for the elevator going down cause of mass*gravity
    elevatorLeader.config_kF(1, .2697);
    elevatorLeader.config_kP(1, 0.2026);
    elevatorLeader.config_kI(1, .001);
    elevatorLeader.config_kD(1, 16);
    elevatorLeader.config_IntegralZone(1, 500);


    elevatorLeader.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
    elevatorLeader.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);

    elevatorLeader.configNominalOutputForward(0);
    elevatorLeader.configNominalOutputReverse(0);
    elevatorLeader.configPeakOutputForward(1);
    elevatorLeader.configPeakOutputReverse(-1);

    elevatorLeader.configMotionCruiseVelocity(3250);
    elevatorLeader.configMotionAcceleration(9750);

  }
  public double motorOutput(){

    return elevatorLeader.getMotorOutputPercent();

  }


  public void GoToTarget(double target){
    if(Math.abs(target-this.GetCurrentPosition())>1000){
      if(target > this.GetCurrentPosition()){
        elevatorLeader.selectProfileSlot(0, 0);
     }
     else{
       elevatorLeader.selectProfileSlot(1, 0);
     }
    }
    elevatorLeader.set(ControlMode.MotionMagic, target);
  }

  public double GetCurrentPosition(){
    return elevatorLeader.getSelectedSensorPosition();
  }

  public double ReadEncoder(){
    return elevatorLeader.getSelectedSensorPosition();
  }

  public double ReadVelocity(){
    return elevatorLeader.getSelectedSensorVelocity();
  }


  public void ManualControl(double output){
    elevatorLeader.set(ControlMode.PercentOutput, output);
  }

  public void ZeroOutEncoder(){
    elevatorLeader.setSelectedSensorPosition(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new MoveElevatorPositions());
  }

public void DriveElevator(double output){
  elevatorLeader.set(ControlMode.PercentOutput, output);
}

public double GetElevatorEncoder(){
  return elevatorLeader.getSelectedSensorPosition();
}

}
