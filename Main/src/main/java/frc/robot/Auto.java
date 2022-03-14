package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.playingwithfusion.CANVenom.ControlMode;
//import com.ctre.phoenix.motorcontrol.ControlMode;


public class Auto extends RobotSubsystems{
    static Timer time = new Timer();
    static int state;

  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", Default);
    m_chooser.addOption("My Auto", kCustomAuto);
    m_chooser.addOption("Auto 40 pos", kCustomAuto1);
    SmartDashboard.putData("Auto choices", m_chooser);
    }

    public static final String Default = "Default";
    public static final String kCustomAuto = "My Auto";
    public static final String kCustomAuto1 = "40 Auto";
    public String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();

    /*<p>You can add additional auto modes by adding additional comparisons to the switch structure
    * below with additional strings. If using the SendableChooser make sure to add them to the
    * chooser code above as well.*/

    public void autonomousInit() {
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
        Drive.rightFront.setCommand(ControlMode.PositionControl, 10);
        Drive.leftFront.setCommand(ControlMode.PositionControl, 10);
      } else {
        switch (m_autoSelected) {
          case kCustomAuto:
          Shooter.shooterRPM = 700;
          Drive.rightFront.setCommand(ControlMode.PositionControl, 10);
          Drive.leftFront.setCommand(ControlMode.PositionControl, 10);
            break;
          case kCustomAuto1:
          Shooter.shooterRPM = 700;
          Drive.rightFront.setCommand(ControlMode.PositionControl, 40);
          Drive.leftFront.setCommand(ControlMode.PositionControl, 40);
            break;
          case Default:
          default:
            Shooter.shooterRPM = 700;
            double targetVelocity_UnitsPer100ms = Shooter.shooterRPM * 4096.0 / 600.0;
            Hopper.kicker.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.6);
            Shooter.shooter.set(com.ctre.phoenix.motorcontrol.ControlMode.Velocity, targetVelocity_UnitsPer100ms);
            if (time.get() > 3) {
              Shooter.shooter.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
              Hopper.kicker.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, 0.0);
              Drive.rightFront.setCommand(ControlMode.PositionControl, -85.0);
              Drive.leftFront.setCommand(ControlMode.PositionControl, -85.0);
              Drive.rightBack.setCommand(ControlMode.PositionControl, -85.0);
              Drive.leftBack.setCommand(ControlMode.PositionControl, -85.0);
            }
            //Drive.leftFront.setCommand(ControlMode.VoltageControl, -5);
            //Drive.rightFront.setCommand(ControlMode.VoltageControl, -5);
        }
      }
    }
    public void testInit()  {}
}
