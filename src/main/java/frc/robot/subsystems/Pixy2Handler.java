/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.TargetLine;

/**
 * Add your docs here.
 */
public class Pixy2Handler extends Subsystem {
  
  public double vectorX0 = 0; // X location of tail of vector
  public double vectorX1 = 0; // X location of head of vector
  public double vectorY0 = 0; // Y location of tail of vector
  public double vectorY1 = 0; // Y location of head of vector

  private double xLeftLimit = 0;
  private double xRightLimit = 78;
  private double yTopLimit = 0;
  private double yBottomLimit = 51;
  

  public void setVectorX0(double x0){
    vectorX0 = x0;
  }

  public double getVectorX0(){
    return vectorX0;
  }
  public void setVectorX1(double x1){
    vectorX1 = x1;
  }

  public double getVectorX1(){
    return vectorX1;
  }

  public void setVectorY0(double y0){
    vectorY0 = y0;
  }

  public double getVectorY0(){
    return vectorY0;
  }

  public void setVectorY1(double y1){
    vectorY1 = y1;
  }

  public double getVectorY1(){
    return vectorY1;
  }



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TargetLine());
  }
}
