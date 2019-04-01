/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class VacuumPump extends Subsystem {
   
  VictorSP pump1 = new VictorSP(8);
  //VictorSP pump2 = new VictorSP(9);


  public void runPumps(){
    pump1.set(-1.0);
   // pump2.set(.3);
  }

  public void stopPumps(){
    pump1.set(0);
   // pump2.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}