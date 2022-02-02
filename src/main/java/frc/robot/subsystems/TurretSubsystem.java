// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {
  /** Creates a new TurretSubsystem. */
  VictorSPX turretMotor = new VictorSPX(TurretConstants.kTurretMasterId);

  public TurretSubsystem() {
    turretMotor.setInverted(TurretConstants.kTurretInvertedMode); // Motor yönünü belirler
    // turretMotor.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme
  }

  public void runTurret(double speed) {
    turretMotor.set(VictorSPXControlMode.PercentOutput, speed);
  }

  public void stopTurret() {
    turretMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
