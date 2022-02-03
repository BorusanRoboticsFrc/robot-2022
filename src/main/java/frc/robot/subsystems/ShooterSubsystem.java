// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  VictorSPX shooterMotor = new VictorSPX(ShooterConstants.kShooterMasterId);

  public ShooterSubsystem() {
    shooterMotor.setInverted(ShooterConstants.kShooterInvertedMode); // Motor yönünü belirler
    // shooterMotor.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme
  }

  public void runShooter(double speed) {
    shooterMotor.set(VictorSPXControlMode.PercentOutput, speed);
  }

  public void stopShooter() {
    shooterMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
