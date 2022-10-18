package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ShooterTest extends RobotSubsystems {
    public WPI_TalonSRX shooter = new WPI_TalonSRX(1);
    public WPI_TalonSRX belt = new WPI_TalonSRX(2);

    public double shooterSpeed = 0.8;

    public void teleopInit(){
        shooter.set(ControlMode.PercentOutput, 0);
        belt.set(ControlMode.PercentOutput, 0);
    }

    public void teleopPeriodic(){
        if(Robot.controllerOne.getXButtonPressed()){
            shooter.set(ControlMode.PercentOutput, shooterSpeed);
        }else if(Robot.controllerOne.getYButtonPressed()){
            shooter.set(ControlMode.PercentOutput, 0);
        }

        if(Robot.controllerOne.getBButton()){
            belt.set(ControlMode.PercentOutput, shooterSpeed);
        }else{
            belt.set(ControlMode.PercentOutput, 0);
        }
    }
}
