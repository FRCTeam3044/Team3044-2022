package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;

/**Handles all encoders, cameras, and general outputs to the driverstation */
public class Display extends RobotSubsystems{
    int loopCount = 0;

    public void robotInit() {
        CameraServer.startAutomaticCapture();
    }

    public void robotPeriodic() {
       int printskips = 50;
       
        if (loopCount % printskips == 0) {
            SmartDashboard.putNumber("left input", Drive.leftFront.get());
            SmartDashboard.putNumber("right input", Drive.rightFront.get());
            SmartDashboard.putNumber("left output", Drive.leftFront.getOutputVoltage());
            SmartDashboard.putNumber("right output", Drive.rightFront.getOutputVoltage());
            SmartDashboard.putNumber("left position", Drive.leftFront.getPosition());
            SmartDashboard.putNumber("right position", Drive.rightFront.getPosition());
            SmartDashboard.putNumber("left speed", Drive.leftFront.getSpeed());
            SmartDashboard.putNumber("right speed", Drive.rightFront.getSpeed());
            SmartDashboard.putNumber("left PID target", Drive.leftFront.getPIDTarget());
            SmartDashboard.putNumber("right PID target", Drive.rightFront.getPIDTarget());
            SmartDashboard.putNumber("current LF", Drive.leftFront.getOutputCurrent());
            SmartDashboard.putNumber("current LB", Drive.leftBack.getOutputCurrent());
            SmartDashboard.putNumber("current RF", Drive.rightFront.getOutputCurrent());
            SmartDashboard.putNumber("current RB", Drive.rightBack.getOutputCurrent());    
        }
        loopCount++;
    }

    public void teleopInit() {
        CameraServer.startAutomaticCapture();
    }

    public void teleopPeriodic() {}

    public void autonomousInit() {}

    public void autonomousPeriodic() {}

    public void testInit() {
        CameraServer.startAutomaticCapture();
    }

    public void testPeriodic() {}

    /**Resets all encoder values for all 4 venom motors */

}
