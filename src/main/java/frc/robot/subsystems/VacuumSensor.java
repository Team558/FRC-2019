/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class VacuumSensor extends Subsystem {

  
  Solenoid powerTheDucer = new Solenoid(6);
  AnalogInput theDucer = new AnalogInput(0);

  public double getAverageVoltage(){
    return theDucer.getAverageVoltage();
  }

  public double getAveragePressure(){
    double aVoltage = this.getAverageVoltage();
    return  ((aVoltage * 11.175) - 25.875);
  }

  public void turnOnDucer(){
    powerTheDucer.set(true);
  }

  public void turnOffDucer(){
    powerTheDucer.set(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
