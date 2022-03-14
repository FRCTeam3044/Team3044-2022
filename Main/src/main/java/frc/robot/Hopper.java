package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/** Amanda do this one */
public class Hopper extends RobotSubsystems {
    /** Put Talon creation and IDs here*/

    public static TalonSRX backRoller = new TalonSRX(8);
    public static TalonSRX frontRoller = new TalonSRX(7);
    public static TalonSRX kicker = new TalonSRX(9);
    //private final WPI_TalonSRX topRoller = new WPITalonSRX(InputIDHere);
    
    public void robotInit() {
        /** Set talons following eachother here */
        backRoller.configFactoryDefault();
        frontRoller.configFactoryDefault();
        kicker.configFactoryDefault();
       
        backRoller.follow(frontRoller);
        kicker.setInverted(true);
        backRoller.setInverted(false);
        frontRoller.setInverted(false);
        backRoller.setNeutralMode(NeutralMode.Coast);
        frontRoller.setNeutralMode(NeutralMode.Coast);
        kicker.setNeutralMode(NeutralMode.Coast);
    }

    public void robotPeriodic() {}

    public void teleopInit() {}

    public void teleopPeriodic() {
        /** When calling on controllers use Robot.controllerOne.etc or Robot.controllerTwo.etc */
        if(Robot.controllerTwo.getYButton()){
            kicker.set(ControlMode.PercentOutput, -0.6);
            frontRoller.set(ControlMode.PercentOutput, 0.4);
        } else {
            frontRoller.set(ControlMode.PercentOutput, 0.0);
            kicker.set(ControlMode.PercentOutput, 0.0);
        }

        if(Robot.controllerTwo.getBackButton()){
            kicker.set(ControlMode.PercentOutput, 0.6);
        }

    }

    public void autonomousInit() {}

    public void autonomousPeriodic() {}

    public void testInit() {}

    public void testPeriodic() {
        /** When calling on controllers use Robot.controllerOne.etc or Robot.controllerTwo.etc */
        if(Robot.controllerTwo.getYButton()){
            frontRoller.set(ControlMode.PercentOutput, 0.4);
            kicker.set(ControlMode.PercentOutput, -0.2);
        } else {
            frontRoller.set(ControlMode.PercentOutput, 0.0);
            kicker.set(ControlMode.PercentOutput, 0.0);
        }
        if(Robot.controllerTwo.getBackButton()){
            kicker.set(ControlMode.PercentOutput, 0.6);
        } else {
            kicker.set(ControlMode.PercentOutput, 0.0);
        }
        if(Robot.controllerTwo.getRightTriggerAxis() > 0.9){
            kicker.set(ControlMode.Velocity, 0.6);
        } else {
            kicker.set(ControlMode.PercentOutput, 0.0);
        }
    }
}