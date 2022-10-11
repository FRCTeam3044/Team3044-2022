package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Drive extends RobotSubsystems
{
  //public static CANVenom leftBack = new CANVenom(4);
  public static WPI_TalonSRX rightFront = new WPI_TalonSRX(4);
  public static WPI_TalonSRX rightBack = new WPI_TalonSRX(2);
  public static WPI_TalonSRX leftBack = new WPI_TalonSRX(3);
  public static WPI_TalonSRX leftFront = new WPI_TalonSRX(1);

  //private DifferentialDrive m_myRobot;
  //public static MotorControllerGroup leftMotors = new MotorControllerGroup(leftFront, leftBack);
  //public static MotorControllerGroup rightMotors = new MotorControllerGroup(rightFront, rightBack);

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

    //m_myRobot = new DifferentialDrive(leftFront, rightFront);
  }
  public void robotPeriodic() {
  }

  public void teleopInit() {
  }

  public void teleopPeriodic() {
    nonlinearDrive(Robot.controllerOne.getLeftY(), Robot.controllerOne.getRightY());
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


  /*public void linearDrive(double left, double right)  {
    leftFront.set(-left);
    rightFront.set(-right);
    leftBack.set(-left);
    rightBack.set(ControlMode.Velocity, -right);
  }*/
}