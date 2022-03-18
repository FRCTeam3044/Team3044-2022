package frc.robot;

import com.playingwithfusion.*;
import com.playingwithfusion.CANVenom.BrakeCoastMode;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class Drive extends RobotSubsystems
{
  public static CANVenom leftFront = new CANVenom(2);
  //public static CANVenom leftBack = new CANVenom(4);
  public static CANVenom rightFront = new CANVenom(1);
  public static CANVenom rightBack = new CANVenom(3);
  public static TalonSRX leftBack = new TalonSRX(3);

  //public static MotorControllerGroup leftMotors = new MotorControllerGroup(leftFront, leftBack);
  //public static MotorControllerGroup rightMotors = new MotorControllerGroup(rightFront, rightBack);

  public void robotInit() {
    leftFront.setB(0);
    leftFront.setKF(0.184);
    leftFront.setKP(0.05);
    leftFront.setKI(0.0);
    leftFront.setKD(0.0);

    rightFront.setB(0);
    rightFront.setKF(0.184);
    rightFront.setKP(0.05);
    rightFront.setKI(0.0);
    rightFront.setKD(0.0);

    leftFront.setInverted(false);
    leftBack.setInverted(false);
    rightFront.setInverted(true);
    rightBack.setInverted(true);

    rightBack.follow(rightFront);
    //rightBack.follow(rightFront); replaced with Talon on CIM for RB

    leftFront.setBrakeCoastMode(BrakeCoastMode.Coast);
    //leftBack.setBrakeCoastMode(BrakeCoastMode.Coast);
    leftBack.setNeutralMode(NeutralMode.Coast);
    rightFront.setBrakeCoastMode(BrakeCoastMode.Coast);
    rightBack.setBrakeCoastMode(BrakeCoastMode.Coast);
  }
  public void robotPeriodic() {
  }

  public void teleopInit() {
    venomEncoderReset();
  }

  public void teleopPeriodic() {
    exponentialDrive(deadband(Robot.controllerOne.getLeftY()), deadband(Robot.controllerOne.getRightY()));
    talonSRXFollowVenom(leftFront, leftBack, 6);
  }
  public static void venomEncoderReset() {
    //leftBack.resetPosition();
    leftFront.resetPosition();
    rightBack.resetPosition();
    rightFront.resetPosition();
  }
  
  public void autonomousInit() {
    venomEncoderReset();
  }

  public void autonomousPeriodic() {}

  public void testInit() {
    venomEncoderReset();
  }

  /*public void testPeriodic() {
    if(Robot.controllerOne.getXButton())  {
      linearDrive(0.3, 0.3);
    }
     if(Robot.controllerOne.getStartButton())  {
      linearDrive(-0.3, -0.3);
    }
  }*/

  public static void talonSRXFollowVenom(CANVenom leader, TalonSRX follower, double maxMotorVolt) {
    if(leader.getOutputVoltage() > 10)  {follower.configVoltageCompSaturation(maxMotorVolt);}
    else  {follower.configVoltageCompSaturation(maxMotorVolt*2);}
    follower.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, leader.getOutputVoltage()/maxMotorVolt);
  }

  /*public void nonlinearDrive(double left, double right) {
    double deadband = 0.11;

    if(Math.abs(left) > deadband){
      leftFront.set(-1.0*Math.signum(left)*left*left);
    } else {leftFront.set(0.0);}
    if(Math.abs(right) > deadband){
      rightFront.set(-1.0*Math.signum(right)*right*right);
    } else {rightFront.set(0.0);}
    if(Math.abs(left) > deadband){
      leftBack.set(-1.0*Math.signum(left)*left*left);
    } else {leftBack.set(0.0);}
    if(Math.abs(left) > deadband){
      rightBack.set(-1.0*Math.signum(right)*right*right);
    } else {rightBack.set(0.0);}
  }*/

  public double deadband(double input) {
    double output;
    if(Math.abs(input) < 0.15) {
      output = 0;
    } else {
      output = input;
    }
    return output;
  }

  /*public void linearDrive(double left, double right)  {
    leftFront.set(-left);
    rightFront.set(-right);
    leftBack.set(-left);
    rightBack.set(ControlMode.Velocity, -right);
  }*/
  
  public void exponentialDrive(double left, double right) {
    leftFront.set(-(0.2*Math.tan(1.4*left)));
    //leftBack.set(TalonSRXControlMode.PercentOutput, -(0.2*Math.tan(1.4*left)));
    rightFront.set(-(0.2*Math.tan(1.4*right)));
    rightBack.set(-(0.2*Math.tan(1.4*right)));
  }
}