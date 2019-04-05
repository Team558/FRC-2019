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
  
  public static double vectorX0 = 0; // X location of tail of vector
  public static double vectorX1 = 0; // X location of head of vector
  public static double vectorY0 = 0; // Y location of tail of vector
  public static double vectorY1 = 0; // Y location of head of vector

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

  public static double getTheta(){
    double theta;
    if((vectorY0-vectorY1)==0){
        theta = 0;
    }
    else {
        theta = Math.toDegrees(Math.atan((vectorX1 - vectorX0)/(vectorY0-vectorY1)));
    }
    return (theta-25);
}

public static double getDx(){
    double dx;
    dx = ((vectorX1+vectorX0)/2)-39;
    return dx;
}

/**
* Sets wheel adjustment based on pixy line following
* 
* @param k1 scaling factor for angle
 * 
 * @param k2 scaling factor for x offset
* 
* @return wheel adjustment for driving
*/

public static double getWheel(double k1, double k2){
    return k1*getTheta()+k2*getDx();
}



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TargetLine());
  }
}
