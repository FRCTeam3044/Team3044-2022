package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Auto extends RobotSubsystems{
    static Timer time = new Timer();
    static int state;

  public void robotInit() {
    m_chooser.setDefaultOption("Default Comp. Auto", Default);
    m_chooser.addOption("2 Ball expirement", kCustomAuto);
    m_chooser.addOption("Auto 40 pos", kCustomAuto1);
    SmartDashboard.putData("Auto choices", m_chooser);
    }

    public static final String Default = "Default Comp. Auto";
    public static final String kCustomAuto = "2 Ball expirement";
    public static final String kCustomAuto1 = "Auto 40";
    public String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();

    /*<p>You can add additional auto modes by adding additional comparisons to the switch structure
    * below with additional strings. If using the SendableChooser make sure to add them to the
    * chooser code above as well.*/

    /*public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);

    state = 0;
    time.reset();
    time.start();
    }

    /** This autonomous (along with the chooser code above) shows how to select between different
    * autonomous modes using the dashboard. The sendable chooser code works with the Java
     * SmartDashboard.*/

    /** TO DO find why always coming up null likely an issue on smart dashboard */
    public void autonomousPeriodic() {
      if(m_autoSelected == null)  {
        Shooter.shooterRPM = 700;
          double targetVelocity_UnitsPer100ms = Shooter.shooterRPM * 4096.0 / 600.0;
          Shooter.kicker.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.6);
          Hopper.frontRoller.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.4);
          Shooter.shooter.set(com.ctre.phoenix.motorcontrol.ControlMode.Velocity, targetVelocity_UnitsPer100ms);
          if (time.get() > 3) {
            Shooter.shooter.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
            Shooter.kicker.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
            Hopper.frontRoller.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
            Drive.rightFront.set(0.5);
            Drive.leftFront.set(0.5);
          } else if (time.get() > 6)  {
            Drive.rightFront.set(0);
            Drive.leftFront.set(0);
          } 
      } else {
        switch (m_autoSelected) {
          case kCustomAuto:
          Shooter.shooterRPM = 700;
          double targetVelocity_UnitsPer100ms = Shooter.shooterRPM * 4096.0 / 600.0;
          Shooter.kicker.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.6);
          Hopper.frontRoller.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.4);
          Shooter.shooter.set(com.ctre.phoenix.motorcontrol.ControlMode.Velocity, targetVelocity_UnitsPer100ms);
          if (time.get() > 3) {
            Shooter.shooter.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
            Shooter.kicker.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
            Hopper.frontRoller.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
            Drive.rightFront.set(0.5);
            Drive.leftFront.set(0.5);
          } else if (time.get() > 6)  {
            Drive.rightFront.set(0);
            Drive.leftFront.set(0);
          }/* else if (time.get() > 8)  {
            Intake.rightSolenoid.set(Value.kForward);
            Intake.leftSolenoid.set(Value.kForward);
            Intake.intakeMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.8);
          } else if (time.get() > 9.5) {
            Hopper.frontRoller.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.4);
            Intake.rightSolenoid.set(Value.kReverse);
            Intake.leftSolenoid.set(Value.kReverse);
          } else if (time.get() > 12) {
            Drive.rightFront.setCommand(ControlMode.PositionControl, -42.03);
            Drive.leftFront.setCommand(ControlMode.PositionControl, 42.03);
          }*/
            break;
          /*case kCustomAuto1:
          Shooter.shooterRPM = 700;
          Drive.rightFront.setCommand(ControlMode.PositionControl, 40);
          Drive.leftFront.setCommand(ControlMode.PositionControl, 40);
            break;
          case Default:
          default:
            //Drive.rightFront.setCommand(ControlMode.PositionControl, -60);
            //Drive.leftFront.setCommand(ControlMode.PositionControl, -60);
            //
            //if (time.get() > 2)
            Shooter.shooterRPM = 700;
            double defaultTargetVelocity_UnitsPer100ms = Shooter.shooterRPM * 4096.0 / 600.0;
            Shooter.kicker.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.6);
            Hopper.frontRoller.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.4);
            Shooter.shooter.set(com.ctre.phoenix.motorcontrol.ControlMode.Velocity, defaultTargetVelocity_UnitsPer100ms);
            if (time.get() > 3) {
              Shooter.shooter.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
              Shooter.kicker.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
              Hopper.frontRoller.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
              Drive.rightFront.setCommand(ControlMode.PositionControl, -85.0);
              Drive.leftFront.setCommand(ControlMode.PositionControl, -85.0);
            }*/
        }
      }
    }
    public void testInit()  {}
}
