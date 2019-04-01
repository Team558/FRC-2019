/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ClimbCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.RunManualClimber;

public class ClimbSequence extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbSequence() {
    addSequential(new TurnOnPumps());
    addSequential(new VacLowPos());
    addSequential(new VacMidPos());
    addSequential(new RunManualClimber());
  }
}
