/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  public static OI m_oi;
  public static CargoIntake cargoIntake = new CargoIntake();
  public static Drivetrain drivetrain = new Drivetrain();
  public static VacuumClimber climber = new VacuumClimber();
  public static VacuumPump pump = new VacuumPump();
  public static VacuumSensor transducer = new VacuumSensor();
  public static Elevator elevator = new Elevator();
  public static HatchExtender hatchExtender = new HatchExtender();
  public static HatchGrabber hatchGrabber = new HatchGrabber();
  public static CargoTater cargoTater = new CargoTater();
  public static CargoDetector cargoDetector = new CargoDetector();
  public static Digit digitBoard = Digit.getInstance();
  public static UsbCamera camera = null;


  public static Compressor pcm = new Compressor();
	public static Relay compressor = new Relay(0);
	

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();




  @Override
  public void robotInit() {
    m_oi = new OI();
    camera = CameraServer.getInstance().startAutomaticCapture();
    //if (camera != null){
      camera.setResolution(320,240); //640x480 or 500x375
      camera.setPixelFormat(PixelFormat.kMJPEG);
      camera.setBrightness(40);
      camera.setFPS(20); //10 or 20

      //Camera.setup();
          //}
  }

  
  @Override
  public void robotPeriodic() {
    //Camera.run();

    
  }

  
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    Robot.pump.stopPumps();

   
    int val = (int) digitBoard.getPotentiometer();

    switch(val){
      case 1:
        digitBoard.writeDigits("ADI");
      case 2:
        digitBoard.writeDigits("ABUL");
      case 3:
        digitBoard.writeDigits("SHIV");
      case 4:
        digitBoard.writeDigits("ALAN");
      case 5:
        digitBoard.writeDigits("MANI");
      case 6:
        digitBoard.writeDigits("ANDY");

    }

  }

  
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

  
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    this.CompressorHandler();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    transducer.turnOnDucer();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //this.CompressorHandler();
    this.transducerHandler();
    
    SmartDashboard.putNumber("Elevator Encoder", Robot.elevator.GetCurrentPosition());
    SmartDashboard.putNumber("Right Drive Encoder", Robot.drivetrain.readRightEncoder());
    SmartDashboard.putNumber("Left Drive Encoder", Robot.drivetrain.readLeftEncoder());
    SmartDashboard.putBoolean("Cargo Detector", Robot.cargoDetector.readSensor());
    SmartDashboard.putNumber("climber Encoder", climber.readVacEncoder());
    SmartDashboard.putNumber("Transducer Voltage", transducer.getAverageVoltage());
    SmartDashboard.putNumber("Transducer Pressure", transducer.getAveragePressure());
    SmartDashboard.putNumber("Climber Velocity", climber.readVacEncoderVel());

 
  }

  @Override
  public void testPeriodic() {
  }
  

  public void CompressorHandler(){
    if (!pcm.getPressureSwitchValue()){
			compressor.set(Value.kForward);
		}
		else {
			compressor.set(Value.kOff);
    }
		
  }

  public void transducerHandler(){
    if (transducer.getAverageVoltage() > 7){
      transducer.turnOffDucer();
      DriverStation.reportError("Transducer Error: Excessive Voltage Check Resistor Setup", true);
    }
  }
  
}
