/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


public class VacuumClimber extends Subsystem {
  TalonSRX climber = new TalonSRX(3);
  TalonSRX climberSlave = new TalonSRX(12);


  public VacuumClimber(){

    climberSlave.follow(climber);

    climber.setInverted(false);
    climberSlave.setInverted(true);

    climber.setSensorPhase(true);
    
    climber.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

    climber.configNominalOutputForward(0, 0);
		climber.configNominalOutputReverse(0, 0);
		climber.configPeakOutputForward(1.0, 0);
    climber.configPeakOutputReverse(-1.0, 0);


    climber.configMotionCruiseVelocity(3300);
    climber.configMotionAcceleration(8000);

    climber.config_kF(0, .31);
    climber.config_kP(0, .0967);
    climber.config_kI(0, 0);
    climber.config_kD(0, .767);


  }
  public double readVacEncoder(){

    return climber.getSelectedSensorPosition();

  }

  public void GoToTarget(double encoderPos){

    climber.set(ControlMode.MotionMagic, encoderPos);

  }

  public double readVacEncoderVel(){
    return climber.getSelectedSensorVelocity();
  }

  public void resetVacEncoder(){

    climber.setSelectedSensorPosition(0);

  }



  public void runClimber(double speed){
    climber.set(ControlMode.PercentOutput, speed);
   // climberSlave.set(ControlMode.PercentOutput, speed);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new RunManualClimber());
  }
}
