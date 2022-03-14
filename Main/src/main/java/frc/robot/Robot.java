// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

/** The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.*/
public class Robot extends TimedRobot {
  
  /** To call on these type Robot.controller# */
  public static XboxController controllerOne = new XboxController(0);
  public static XboxController controllerTwo = new XboxController(1);

  public static Display Display;
  public static Drive Drive;
  public static Climb Climb;
  public static Intake Intake;
  public static Hopper Hopper;
  public static Shooter Shooter;
  public static Auto Auto;
  
  // This method should (a) initalize the Subsystems collection and 
  // (b) call robotInit on all Subsystem modules
  void initializeSystems()  {
    Robot.Drive = new Drive();
    Robot.Intake = new Intake();
    Robot.Hopper = new Hopper();
    Robot.Shooter = new Shooter();
    Robot.Climb = new Climb();
    Robot.Auto = new Auto();
    Robot.Display = new Display();
  }

  /** This function is run when the robot is first started up and should be used for any
   * initialization code.*/
  @Override
  public void robotInit() {
    initializeSystems();

    Robot.Drive.robotInit();
    Robot.Intake.robotInit();
    Robot.Hopper.robotInit();
    Robot.Shooter.robotInit();
    Robot.Climb.robotInit();
    Robot.Auto.robotInit();
    Robot.Display.robotInit();
  }

  /** This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.*/
  @Override
  public void robotPeriodic() {
    Robot.Drive.robotPeriodic();
    Robot.Intake.robotPeriodic();
    Robot.Hopper.robotPeriodic();
    Robot.Shooter.robotPeriodic();
    Robot.Climb.robotPeriodic();
    Robot.Auto.robotPeriodic();
    Robot.Display.robotPeriodic();
  }

  /** This function is called once when autonomous is enabled. */
  @Override
  public void autonomousInit() {
    Robot.Drive.autonomousInit();
    Robot.Intake.autonomousInit();
    Robot.Hopper.autonomousInit();
    Robot.Shooter.autonomousInit();
    Robot.Climb.autonomousInit();
    Robot.Auto.autonomousInit();
    Robot.Display.autonomousInit();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    Robot.Drive.autonomousPeriodic();
    Robot.Intake.autonomousPeriodic();
    Robot.Hopper.autonomousPeriodic();
    Robot.Shooter.autonomousPeriodic();
    Robot.Climb.autonomousPeriodic();
    Robot.Auto.autonomousPeriodic();
    Robot.Display.autonomousPeriodic();
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    Robot.Drive.teleopInit();
    Robot.Intake.teleopInit();
    Robot.Hopper.teleopInit();
    Robot.Shooter.teleopInit();
    Robot.Climb.teleopInit();
    Robot.Auto.teleopInit();
    Robot.Display.teleopInit();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    Robot.Drive.teleopPeriodic();
    Robot.Intake.teleopPeriodic();
    Robot.Hopper.teleopPeriodic();
    Robot.Shooter.teleopPeriodic();
    Robot.Climb.teleopPeriodic();
    Robot.Display.teleopPeriodic();
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
    Robot.Drive.testInit();
    Robot.Intake.testInit();
    Robot.Hopper.testInit();
    Robot.Shooter.testInit();
    Robot.Climb.testInit();
    Robot.Auto.testInit();
    Robot.Display.testInit();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    Robot.Drive.testPeriodic();
    Robot.Intake.testPeriodic();
    Robot.Hopper.testPeriodic();
    Robot.Shooter.testPeriodic();
    Robot.Climb.testPeriodic();
    Robot.Auto.testPeriodic();
    Robot.Display.testPeriodic();
  }
  
  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}
}