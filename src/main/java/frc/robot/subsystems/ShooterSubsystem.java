// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  public boolean isAtSetpoint = false;
  int i;
  public boolean isRunning = false;
  double rpmSum;
  private WPI_VictorSPX shooterMotor = new WPI_VictorSPX(ShooterConstants.kShooterMasterId);
  public final Encoder shooterEncoder;

  public ShooterSubsystem() {
    shooterMotor.configFactoryDefault();
    shooterEncoder = new Encoder(ShooterConstants.kShooterEncoderA, ShooterConstants.kShooterEncoderB,
        ShooterConstants.kShooterEncoderIsReversed);
    shooterEncoder.setDistancePerPulse(1.0 / (ShooterConstants.kShooterEncoderPPR));
  }

  public void runShooter(double speed) {
    shooterMotor.set(speed);
  }

  public void runShooterVoltage(double voltage) {
    shooterMotor.setVoltage(voltage);
  }

  public double getRPM() {
    return shooterEncoder.getRate() * 60;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //addChild("Shooter", shooterMotor);
    SmartDashboard.putBoolean("shooter/running", isRunning);
    SmartDashboard.putNumber("shooter/rpm", getRPM());
  }
}
