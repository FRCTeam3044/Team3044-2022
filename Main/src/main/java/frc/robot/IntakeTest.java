package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeTest extends RobotSubsystems{
    public WPI_TalonSRX shoulder= new WPI_TalonSRX(1);
    public WPI_TalonSRX roller= new WPI_TalonSRX(2);
    public double shoulderSpeed=0.5;
    public double rollerSpeed=0.5;
    public void teleopInit (){
        shoulder.set(ControlMode.PercentOutput, 0);
        roller.set(ControlMode.PercentOutput, 0);
   
    }
    public void teleopPeriodic (){
        if (Robot.controllerOne.getXButton()){
            shoulder.set(ControlMode.PercentOutput, -shoulderSpeed);
        } else if (Robot.controllerOne.getYButton()){
            shoulder.set(ControlMode.PercentOutput, shoulderSpeed);
        } else {
            shoulder.set(ControlMode.PercentOutput, 0);
        }
        if (Robot.controllerOne.getRightTriggerAxis()>0.5){
            roller.set(ControlMode.PercentOutput, rollerSpeed);
        }
        else {
            roller.set(ControlMode.PercentOutput, 0);
        }

    }
}
