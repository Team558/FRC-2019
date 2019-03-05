/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;



public class HatchExtender extends Subsystem {

  DoubleSolenoid quackLauncher = new DoubleSolenoid(2, 3);

  public void fireQuackLauncher(){
    quackLauncher.set(Value.kForward);
  }
  public void retractQuackLauncher(){
    quackLauncher.set(Value.kReverse);
  }

  public Enum getLauncherValue(){

    return quackLauncher.get();


  }


  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
