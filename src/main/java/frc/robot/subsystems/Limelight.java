/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Limelight extends Subsystem {
  

public Limelight(){
  
}

public double isTarget(){
  return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
}

public double getHorizontal(){
  return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
}

public double getVertical(){
  return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
}

public double getArea(){
  return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
}

public double getSkew(){
  return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
}

public void setLEDMode(double value){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(value);
}


public void setCamMode(double value){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(value);
}

public void setPipeline(double value){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(value);
}

public void setStream(double value){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(value);
}
/*public double getDistance(){
  double x = getArea();
  double y = (-1.656*Math.log(x) + 6.0243);
  return (y * 12);

}*/
  @Override
  public void initDefaultCommand() {
  }
}
