package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/** John do this one */
public class Shooter extends RobotSubsystems {
    /** Put Talon creation and IDs here*/
    public static TalonSRX shooter = new TalonSRX(14);
    public static TalonSRX kicker = new TalonSRX(9);
    double shooterMagicVelocityPercentValue = 0.30;
    public static double shooterRPM = 700;

    public void robotInit() {
        /** Set talons following eachother here */
        int talonPIDslot = 0;
        int talonTimeOut = 20;
        
        int magicMaxVelocity = 0;
        int magicMaxAccel = 0;
        //double shooterVelocityPercentValue = 0.7;
        double shooterMotorVelocityValue = 8000;
        int shooterMagicScurveValue = 0;
        Boolean shooterMotorDirectionInvert = true;
        Boolean shooterEncoderPhaseInvert = true;
        Boolean shooterEnableVoltCompValue = true;
        double shooterRescaleFullVoltsValue = 11.0;
        double shooterOpenLoopTalonRampSecs = 0.5;
        double shooterClosedLoopRampSecsValue = 0.2;
        double shooterMagicAccelSecondsValue = 0.2;
        double shooterDeadband = 0.001;
        int maxPIDerrAllowance = 200;
        double shooterKf = 0.1;
        magicMaxVelocity = Math.toIntExact(Math.round( shooterMotorVelocityValue * shooterMagicVelocityPercentValue));
        magicMaxAccel = Math.toIntExact(Math.round(shooterMotorVelocityValue / shooterMagicAccelSecondsValue));
        
        shooter.configFactoryDefault();
        shooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,talonPIDslot,talonTimeOut);
        shooter.setSensorPhase(shooterEncoderPhaseInvert);
        shooter.set(ControlMode.PercentOutput, 0.0);
        shooter.setNeutralMode(NeutralMode.Coast);
        shooter.config_kF(talonPIDslot, shooterKf);
        shooter.setInverted(shooterMotorDirectionInvert);
        shooter.configVoltageCompSaturation(shooterRescaleFullVoltsValue);
        shooter.enableVoltageCompensation(shooterEnableVoltCompValue);
        shooter.configOpenloopRamp(shooterOpenLoopTalonRampSecs);
        shooter.configClosedloopRamp(shooterClosedLoopRampSecsValue);
        shooter.configMotionSCurveStrength(shooterMagicScurveValue, talonTimeOut);
        shooter.configMotionCruiseVelocity( magicMaxVelocity, talonTimeOut);
        shooter.configMotionAcceleration( magicMaxAccel, talonTimeOut);
        shooter.configAllowableClosedloopError(talonPIDslot, maxPIDerrAllowance);
        shooter.configNeutralDeadband(shooterDeadband);
        shooterRPM = 700;
    }

    public void robotPeriodic() {}

    public void teleopInit() {
        shooter.set(ControlMode.PercentOutput, 0.0);
    }

    public void teleopPeriodic() {
        /** When calling on controllers use Robot.controllerOne.etc or Robot.controllerTwo.etc */

        if(Robot.controllerTwo.getStartButton()){
            shooterRPM = 1200;
        }
        if(Robot.controllerTwo.getAButton()){
            shooterRPM = 700;
        }
        if(Robot.controllerTwo.getLeftTriggerAxis() > 0.9){
            double targetVelocity_UnitsPer100ms = shooterRPM * 4096.0 / 600.0;
            shooter.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
        } else {
            shooter.set(ControlMode.PercentOutput, 0.0);
        }
    }

    public void autonomusInit() {
        shooter.set(ControlMode.PercentOutput, 0.0);
    }

    public void autonomousPeriodic() {}

    public void testInit() {
        shooter.set(ControlMode.PercentOutput, 0.0);
    }

    public void testPeriodic() {}
}