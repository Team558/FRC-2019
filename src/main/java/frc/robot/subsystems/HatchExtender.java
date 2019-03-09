/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;



public class HatchExtender extends Subsystem {

  Solenoid quackLauncher = new Solenoid(1);
  

  public void fireQuackLauncher(){
    quackLauncher.set(true);
  }
  public void retractQuackLauncher(){
    quackLauncher.set(false);
  }
  public boolean readSolenoid(){

    return quackLauncher.get();

  }

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
