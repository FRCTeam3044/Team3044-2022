package frc.robot;

import com.playingwithfusion.*;

public class Drive extends RobotSubsystems
{
  public static CANVenom leftFront = new CANVenom(2);
  public static CANVenom leftBack = new CANVenom(4);
  public static CANVenom rightFront = new CANVenom(1);
  public static CANVenom rightBack = new CANVenom(3);

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

    //leftBack.follow(leftFront);
    //rightBack.follow(rightFront);
    leftFront.setInverted(false);
    leftBack.setInverted(false);
    rightFront.setInverted(true);
    rightBack.setInverted(true);

    /* This is part of a test and feel free to comment it out
    createExponentialDriveTable();
    */
  }
  public void robotPeriodic() {}

  public void teleopInit() {
    venomEncoderReset();
  }

  public void teleopPeriodic() {
    /*
    nonlinearDrive(deadband(Robot.controllerOne.getLeftY()), deadband(Robot.controllerO0ne.getRightY()) );s
    */
    nonlinearDrive(Robot.controllerOne.getLeftY(), Robot.controllerOne.getRightY() );
  }
  public void venomEncoderReset() {
    leftBack.resetPosition();
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

  public void testPeriodic() {
    if(Robot.controllerOne.getXButton())  {
      linearDrive(0.3, 0.3);
    }
     if(Robot.controllerOne.getStartButton())  {
      linearDrive(-0.3, -0.3);
    }
  }

  public void nonlinearDrive(double left, double right) {
    double VenomDeadband = 0.11;

    if(Math.abs(left) > VenomDeadband){
      leftFront.set(-1.0*Math.signum(left)*left*left);
    } else {leftFront.set(0.0);}
    if(Math.abs(right) > VenomDeadband){
      rightFront.set(-1.0*Math.signum(right)*right*right);
    } else {rightFront.set(0.0);}
    if(Math.abs(left) > VenomDeadband){
      leftBack.set(-1.0*Math.signum(left)*left*left);
    } else {leftBack.set(0.0);}
    if(Math.abs(left) > VenomDeadband){
      rightBack.set(-1.0*Math.signum(right)*right*right);
    } else {rightBack.set(0.0);}
  }

  /*
  public double deadband(double input) {
    double output;
    if(Math.abs(input) < 0.15) {
      output = 0;
    } else {
      output = input;
    }
    return output;
  }
  */

  public void linearDrive(double left, double right)  {
    leftFront.set(-left);
    rightFront.set(-right);
    leftBack.set(-left);
    rightBack.set(-right);
  }
  
  /* 
  public void exponentialDrive(double left, double right) {
    leftFront.set(-(0.2*Math.tan(1.4*left)));
    rightFront.set(-(0.2*Math.tan(1.4*right)));
  }
  */

  /* Experiment with creating lookup tables to reduce computational burden of drive function
  public void createExponentialDriveTable() {
    double[] posArray = new double[1999];
    double[] negArray = new double[1999];
    for(int i = 0; i < 1999; i++) {
      posArray[i] = 0.2*Math.tan(1.4*(i/2000));
    }
    for(int i = 0; i < 1999; i++) {
      negArray[i] = 0.2*Math.tan(1.4*-(i/2000));
    }
    System.out.println(posArray + " && " + negArray);
  }
  */
}