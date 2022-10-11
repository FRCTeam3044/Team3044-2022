package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Arm extends RobotSubsystems{
    public static WPI_TalonSRX shoulder = new WPI_TalonSRX(90);
    public static WPI_TalonSRX roller = new WPI_TalonSRX(91);

    private static double lowerShoulderSpeed = 0.5;

    public void teleopInit() {
        //set all to 0 on teleopInit
        shoulder.set(ControlMode.PercentOutput, 0);
        roller.set(ControlMode.PercentOutput, 0);
    } 

    public void teleopPeriodic() {
        if(Robot.controllerTwo.getXButton()) {
            //when held down set to lowerShoulderSpeed
            shoulder.set(ControlMode.PercentOutput, lowerShoulderSpeed);
        } else {
            //when released set to 0
            shoulder.set(ControlMode.PercentOutput, 0);
        }
    } 
}
