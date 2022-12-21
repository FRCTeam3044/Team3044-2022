// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private static WPI_TalonSRX leftFront = new WPI_TalonSRX(1);
  private static WPI_TalonSRX rightFront = new WPI_TalonSRX(2);
  private static WPI_TalonSRX leftBack = new WPI_TalonSRX(3);
  private static WPI_TalonSRX rightBack = new WPI_TalonSRX(4);

  public static MotorControllerGroup leftMotors = new MotorControllerGroup(leftFront, leftBack);
  public static MotorControllerGroup rightMotors = new MotorControllerGroup(rightFront, rightBack);

  private final DifferentialDrive m_drive = new DifferentialDrive(leftMotors, rightMotors);

  public void arcadeDrive(double speed, double rotation) {
    m_drive.arcadeDrive(speed, rotation);
  }

  public DriveSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
