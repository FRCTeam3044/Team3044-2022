package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ElevatorTest extends RobotSubsystems {
    public WPI_TalonSRX motorOne = new WPI_TalonSRX(1);
    public WPI_TalonSRX motorTwo = new WPI_TalonSRX(2);

    public double elevatorSpeed = 0.5;
    
    public void teleopInit(){
        motorOne.set(ControlMode.PercentOutput, 0);
        motorTwo.set(ControlMode.PercentOutput, 0);
    }

    public void teleopPeriodic(){
        if(Robot.controllerOne.getAButton()){
            motorOne.set(ControlMode.PercentOutput, elevatorSpeed);
            motorTwo.set(ControlMode.PercentOutput, elevatorSpeed);
        } else if(Robot.controllerOne.getBButton()){
            motorOne.set(ControlMode.PercentOutput, -elevatorSpeed);
            motorTwo.set(ControlMode.PercentOutput, -elevatorSpeed);
        } else {
            motorOne.set(ControlMode.PercentOutput, 0);
            motorTwo.set(ControlMode.PercentOutput, 0);
        }
    }
}
