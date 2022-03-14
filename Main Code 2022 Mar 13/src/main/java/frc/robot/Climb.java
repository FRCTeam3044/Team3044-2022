package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/** Jordan do this one */
public class Climb extends RobotSubsystems {
    /** When calling on controllers use Robot.controllerOne.etc or Robot.controllerTwo.etc */
    public static TalonSRX rightClimber = new TalonSRX(5);
    public static TalonSRX leftClimber = new TalonSRX(6);
    double climberMagicVelocityPercentValue = 0.30;
    Boolean twoLock = false;
    Boolean oneLock = false; 
    public static double climberRPM = 0;

    public void robotInit() {
        /** Set talons following each other here */
        int talonPIDslot = 0;
        int talonTimeOut = 20;
        
        int magicMaxVelocity = 0;
        int magicMaxAccel = 0;
        double climberMotorVelocityValue = 500;
        int climberMagicScurveValue = 0;
        Boolean climberMotorDirectionInvert = true;
        Boolean climberEncoderPhaseInvert = true;
        Boolean climberEnableVoltCompValue = true;
        double climberRescaleFullVoltsValue = 11.0;
        double climberOpenLoopTalonRampSecs = 0.5;
        double climberClosedLoopRampSecsValue = 0.2;
        double climberMagicAccelSecondsValue = 0.2;
        double climberDeadband = 0.001;
        int maxPIDerrAllowance = 200;
        double shooterKf = 0.1;
        magicMaxVelocity = Math.toIntExact(Math.round( climberMotorVelocityValue * climberMagicVelocityPercentValue));
        magicMaxAccel = Math.toIntExact(Math.round(climberMotorVelocityValue / climberMagicAccelSecondsValue));

        rightClimber.configFactoryDefault();
        rightClimber.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,talonPIDslot,talonTimeOut);
        rightClimber.setSensorPhase(climberEncoderPhaseInvert);
        rightClimber.set(ControlMode.PercentOutput, 0.0);
        rightClimber.setNeutralMode(NeutralMode.Brake);
        rightClimber.config_kF(talonPIDslot, shooterKf);
        rightClimber.configVoltageCompSaturation(climberRescaleFullVoltsValue);
        rightClimber.enableVoltageCompensation(climberEnableVoltCompValue);
        rightClimber.configOpenloopRamp(climberOpenLoopTalonRampSecs);
        rightClimber.configClosedloopRamp(climberClosedLoopRampSecsValue);
        rightClimber.configMotionSCurveStrength(climberMagicScurveValue, talonTimeOut);
        rightClimber.configMotionCruiseVelocity( magicMaxVelocity, talonTimeOut);
        rightClimber.configMotionAcceleration( magicMaxAccel, talonTimeOut);
        rightClimber.configAllowableClosedloopError(talonPIDslot, maxPIDerrAllowance);
        rightClimber.configNeutralDeadband(climberDeadband);
        rightClimber.setInverted(false);

        leftClimber.configFactoryDefault();
        leftClimber.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,talonPIDslot,talonTimeOut);
        leftClimber.setSensorPhase(climberEncoderPhaseInvert);
        leftClimber.set(ControlMode.PercentOutput, 0.0);
        leftClimber.setNeutralMode(NeutralMode.Brake);
        leftClimber.config_kF(talonPIDslot, shooterKf);
        leftClimber.configVoltageCompSaturation(climberRescaleFullVoltsValue);
        leftClimber.enableVoltageCompensation(climberEnableVoltCompValue);
        leftClimber.configOpenloopRamp(climberOpenLoopTalonRampSecs);
        leftClimber.configClosedloopRamp(climberClosedLoopRampSecsValue);
        leftClimber.configMotionSCurveStrength(climberMagicScurveValue, talonTimeOut);
        leftClimber.configMotionCruiseVelocity( magicMaxVelocity, talonTimeOut);
        leftClimber.configMotionAcceleration( magicMaxAccel, talonTimeOut);
        leftClimber.configAllowableClosedloopError(talonPIDslot, maxPIDerrAllowance);
        leftClimber.configNeutralDeadband(climberDeadband);
        leftClimber.setInverted(false);

        climberRPM = 0;
        
        leftClimber.configFactoryDefault();
        leftClimber.setNeutralMode(NeutralMode.Brake);

        oneLock = false;
        twoLock = false;
    }

    public void robotPeriodic() {}

    public void teleopInit() {}

    public void teleopPeriodic() {
        /** When calling on controllers use Robot.controllerOne.etc or Robot.controllerTwo.etc */
        if (oneLock == false){
            if(Robot.controllerTwo.getPOV() == 0){
                twoLock = true;
                /*climberRPM = 500;
                double targetVelocity_UnitsPer100ms = climberRPM * 4096.0 / 600.0;
                rightClimber.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
                leftClimber.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);*/
                rightClimber.set(ControlMode.PercentOutput, 0.6);
                leftClimber.set(ControlMode.PercentOutput, 0.6);
            }
            if(Robot.controllerTwo.getPOV() == 180){
                twoLock = true;
                /*climberRPM = -500;
                double targetVelocity_UnitsPer100ms = climberRPM * 4096.0 / 600.0;
                rightClimber.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
                leftClimber.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);*/
                rightClimber.set(ControlMode.PercentOutput, -0.6);
                leftClimber.set(ControlMode.PercentOutput, -0.6);
            }
            if(Robot.controllerTwo.getPOV() == -1){
                rightClimber.set(ControlMode.PercentOutput, 0.0);
                leftClimber.set(ControlMode.PercentOutput, 0.0);
                twoLock = false;
            }
        }
        if (twoLock == false){
            if(Robot.controllerOne.getPOV()  == 0){
                oneLock = true;
                /*climberRPM = 500;
                double targetVelocity_UnitsPer100ms = climberRPM * 4096.0 / 600.0;
                leftClimber.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);*/
                leftClimber.set(ControlMode.PercentOutput, 0.6);
            }
            if(Robot.controllerOne.getPOV()  == 180){
                oneLock = true;
                /*climberRPM = -500;
                double targetVelocity_UnitsPer100ms = climberRPM * 4096.0 / 600.0;
                leftClimber.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);*/
                leftClimber.set(ControlMode.PercentOutput, -0.6);
            }
            if(Robot.controllerOne.getPOV()  == 90){
                oneLock = true;
                /*climberRPM = 500;
                double targetVelocity_UnitsPer100ms = climberRPM * 4096.0 / 600.0;
                rightClimber.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);*/
                rightClimber.set(ControlMode.PercentOutput, 0.6);
            }
            if(Robot.controllerOne.getPOV()  == 270){
                oneLock = true;
                /*climberRPM = -500;
                double targetVelocity_UnitsPer100ms = climberRPM * 4096.0 / 600.0;
                rightClimber.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);*/
                rightClimber.set(ControlMode.PercentOutput, -0.6);
            }
            if(Robot.controllerOne.getPOV()  == -1){
                rightClimber.set(ControlMode.PercentOutput, 0.0);
                leftClimber.set(ControlMode.PercentOutput, 0.0);
                oneLock = false;
            }
        }
    }

    public void autonomousInit() {}

    public void autonomousPeriodic() {}

    public void testInit() {}

    public void testPeriodic() {
        if(Robot.controllerTwo.getPOV() >= 0 && Robot.controllerTwo.getPOV() <= 0){
            rightClimber.set(ControlMode.PercentOutput, 0.3);
        } else {
            rightClimber.set(ControlMode.PercentOutput, 0);
        }

        if(Robot.controllerTwo.getPOV() >= 170 && Robot.controllerTwo.getPOV() <= 190){
            rightClimber.set(ControlMode.PercentOutput, -0.3);
        } else {
            rightClimber.set(ControlMode.PercentOutput, 0);
        }
    }
}