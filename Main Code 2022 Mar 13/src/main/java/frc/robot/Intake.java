package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/** Gavin do this one */
public class Intake extends RobotSubsystems {
    /** Put Talon and Solenoid creation and IDs here */
    public static TalonSRX intakeMotor = new TalonSRX(12);
   
    Compressor IntakeCompressor = new Compressor(PneumaticsModuleType.CTREPCM);

    DoubleSolenoid rightSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    DoubleSolenoid leftSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

    public void robotInit() {
        /** Set talons and solenoids following eachother here */
        intakeMotor.configFactoryDefault();
        
        rightSolenoid.set(Value.kReverse);
        leftSolenoid.set(Value.kReverse);
        intakeMotor.setInverted(true);
    }
    public void robotPeriodic() {
    }

    public void teleopInit() {
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }

    public void teleopPeriodic() {
        /*if (Robot.controllerTwo.getAButton()) {
            intakeMotor.set(ControlMode.PercentOutput, 1.0);
        } else {
            intakeMotor.set(ControlMode.PercentOutput, 0);
        }
        /*if (Robot.controllerTwo.getStartButton()) {
            intakeMotor.set(ControlMode.PercentOutput, -0.8);
        } else {
            intakeMotor.set(ControlMode.PercentOutput, 0);
        }*/
        if (Robot.controllerTwo.getLeftBumper()) {
            rightSolenoid.set(Value.kForward);
            leftSolenoid.set(Value.kForward);
            /*
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            */
            intakeMotor.set(ControlMode.PercentOutput, 0.8);
        } else{
            rightSolenoid.set(Value.kReverse);
            leftSolenoid.set(Value.kReverse);
            intakeMotor.set(ControlMode.PercentOutput, 0.0);
        }
        if (Robot.controllerTwo.getBButton()) {
            intakeMotor.set(ControlMode.PercentOutput, 0.8);
        }
        /**
         * When calling on controllers use Robot.controllerOne.etc or
         * Robot.controllerTwo.etc
         */

    }

    public void autonomousInit() {
        rightSolenoid.set(Value.kReverse);
        leftSolenoid.set(Value.kReverse);
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }

    public void autonomousPeriodic() {
    }

    public void testInit() {
        rightSolenoid.set(Value.kReverse);
        leftSolenoid.set(Value.kReverse);
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }

    public void testPeriodic() {
        if (Robot.controllerTwo.getAButton()) {
        intakeMotor.set(ControlMode.PercentOutput, 0.8);
        }
        if (Robot.controllerTwo.getLeftBumper()) {
            rightSolenoid.set(Value.kForward);
            leftSolenoid.set(Value.kForward);
        } 
        if (Robot.controllerTwo.getRightBumper()) {
            rightSolenoid.set(Value.kReverse);
            leftSolenoid.set(Value.kReverse);
        }
    }
}