/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.CargoDetector;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.CargoTater;
import frc.robot.subsystems.Digit;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchExtender;
import frc.robot.subsystems.HatchGrabber;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.VacuumClimber;
import frc.robot.subsystems.VacuumPump;
import frc.robot.subsystems.VacuumSensor;

public class Robot extends TimedRobot {
  public static OI m_oi;
  public static CargoIntake cargoIntake = new CargoIntake();
  public static Drivetrain drivetrain = new Drivetrain();
  public static VacuumClimber climber = new VacuumClimber();
  public static VacuumPump pump = new VacuumPump();
  public static VacuumSensor transducer = new VacuumSensor();
  public static Limelight limeLight = new Limelight();
  public static Elevator elevator = new Elevator();
  public static HatchExtender hatchExtender = new HatchExtender();
  public static HatchGrabber hatchGrabber = new HatchGrabber();
  public static CargoTater cargoTater = new CargoTater();
  public static Digit digitBoard = Digit.getInstance();
  public static CargoDetector cargoDetector = new CargoDetector();


  public static Compressor pcm = new Compressor();
	public static Relay compressor = new Relay(0);
	

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();




  @Override
  public void robotInit() {
    m_oi = new OI();

    Robot.climber.rampRateClimber();
    this.limeLight.setStream(0);

    drivetrain.setRampRate();
    Robot.pump.PumpRamprate();
  }

  
  @Override
  public void robotPeriodic() {

    SmartDashboard.putNumber("Elevator Encoder", Robot.elevator.GetElevatorEncoder());
    SmartDashboard.putNumber("Elevator Encoder2", Robot.elevator.GetElevatorEncoder());
    SmartDashboard.putNumber("Climber Encoder", Robot.climber.readVacEncoder());
    SmartDashboard.putBoolean("Cargo Detector", Robot.cargoDetector.readSensor());
    SmartDashboard.putNumber("Transducer Pressure", Robot.transducer.getAveragePressure());
    //SmartDashboard.putNumber("Limelight Distance", Robot.limeLight.getDistance());
  }

  
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    Robot.limeLight.setLEDMode(1);
    Robot.pump.stopPumps();
    Robot.m_oi.setrumble(0);
  }

  
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    Robot.limeLight.setLEDMode(3);

    climber.rampRateClimber();
    drivetrain.setRampRate();
    Robot.pump.PumpRamprate();
  
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    this.CompressorHandler();

    Robot.m_oi.limelightRumble();

  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    limeLight.setLEDMode(3);
    drivetrain.setRampRate();
    climber.rampRateClimber();
    Robot.pump.PumpRamprate();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    this.CompressorHandler();
    SmartDashboard.putNumber("Elevator Encoder", Robot.elevator.GetElevatorEncoder());
    SmartDashboard.putNumber("Vac Motor Output", Robot.climber.MotorOutputClimber());
    SmartDashboard.putNumber("Climber Encoder", Robot.climber.readVacEncoder());
    SmartDashboard.putNumber("LimeLight Area", Robot.limeLight.getArea());

    Robot.m_oi.limelightRumble();
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
