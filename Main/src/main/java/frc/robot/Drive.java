package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Encoder;

public class Drive extends RobotSubsystems
{
  //public static CANVenom leftBack = new CANVenom(4);
  public static WPI_TalonSRX rightFront = new WPI_TalonSRX(4);
  public static WPI_TalonSRX rightBack = new WPI_TalonSRX(2);
  public static WPI_TalonSRX leftBack = new WPI_TalonSRX(3);
  public static WPI_TalonSRX leftFront = new WPI_TalonSRX(1);

  private final DifferentialDrive m_drive = new DifferentialDrive(leftMotors, rightMotors);

  public static MotorControllerGroup leftMotors = new MotorControllerGroup(leftFront, leftBack);
  public static MotorControllerGroup rightMotors = new MotorControllerGroup(rightFront, rightBack);

  public void robotInit() {
    //check how wired
    leftFront.setInverted(false);
    leftBack.setInverted(false);
    rightFront.setInverted(true);
    rightBack.setInverted(true);

    rightBack.follow(rightFront);
    leftBack.follow(leftFront);
    //rightBack.follow(rightFront); replaced with Talon on CIM for RB

    leftFront.setNeutralMode(NeutralMode.Coast);
    leftBack.setNeutralMode(NeutralMode.Coast);
    rightFront.setNeutralMode(NeutralMode.Coast);
    rightBack.setNeutralMode(NeutralMode.Coast);
  }
  public void robotPeriodic() {
  }

  public void teleopInit() {
  }

  public void teleopPeriodic() {
    //nonlinearDrive(Robot.controllerOne.getLeftY(), Robot.controllerOne.getRightY());
    arcadeDrive(-Robot.controllerOne.getLeftY(), Robot.controllerOne.getRightX(), 0.10);
  }
  
  public void autonomousInit() {
  }

  public void autonomousPeriodic() {}

  public void testInit() {
  }

  /*public void testPeriodic() {
    if(Robot.controllerOne.getXButton())  {
      linearDrive(0.3, 0.3);
    }
     if(Robot.controllerOne.getStartButton())  {
      linearDrive(-0.3, -0.3);
    }
  }*/

 

  public void nonlinearDrive(double left, double right) {
    double deadband = 0.11;

    if(Math.abs(left) > deadband){
      leftFront.set(-1.0*Math.signum(left)*left*left);
    } else {leftFront.set(0.0);}
    if(Math.abs(right) > deadband){
      rightFront.set(-1.0*Math.signum(right)*right*right);
    } else {rightFront.set(0.0);}
    /*if(Math.abs(left) > deadband){
      leftBack.set(-1.0*Math.signum(left)*left*left);
    } else {leftBack.set(0.0);}
    if(Math.abs(left) > deadband){
      rightBack.set(-1.0*Math.signum(right)*right*right);
    } else {rightBack.set(0.0);}*/
  }

  public void arcadeDrive(double fwd, double rot, double deadband) {
    if(Math.abs(fwd) < deadband){fwd = 0;}
    if(Math.abs(rot) < deadband){rot = 0;}
    m_drive.arcadeDrive(fwd, rot);
  }
  /*public void linearDrive(double left, double right)  {
    leftFront.set(-left);
    rightFront.set(-right);
    leftBack.set(-left);
    rightBack.set(ControlMode.Velocity, -right);
  }*/
}