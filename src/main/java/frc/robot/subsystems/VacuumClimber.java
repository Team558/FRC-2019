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
import frc.robot.commands.RunManualClimber;

/**
 * Add your docs here.
 */
public class VacuumClimber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX climber = new TalonSRX(12);
  TalonSRX climberSlave = new TalonSRX(3);


  public VacuumClimber(){

    //climberSlave.follow(climber);

    climber.setInverted(false);
    climberSlave.setInverted(true);

    climberSlave.setSensorPhase(true);
    
    climberSlave.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

    climber.configNominalOutputForward(0, 0);
		climber.configNominalOutputReverse(0, 0);
		climber.configPeakOutputForward(1.0, 0);
    climber.configPeakOutputReverse(-1.0, 0);

  }
  public double readVacEncoder(){

    return climberSlave.getSelectedSensorPosition();

  }

  public void runClimber(double speed){
    climber.set(ControlMode.PercentOutput, speed);
    climberSlave.set(ControlMode.PercentOutput, speed);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new RunManualClimber());
  }
}
