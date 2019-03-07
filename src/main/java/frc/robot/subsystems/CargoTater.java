/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * Add your docs here.
 */
public class CargoTater extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid cargotron = new DoubleSolenoid(4, 5);

  public void intakeDown(){

    cargotron.set(Value.kForward);

  }

  public void intakeUp(){

    cargotron.set(Value.kReverse);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
