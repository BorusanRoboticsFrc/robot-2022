// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  VictorSPX intakeMotor = new VictorSPX(IntakeConstants.kIntakeMasterId);

  public IntakeSubsystem() {
    intakeMotor.setInverted(IntakeConstants.kIntakeInvertedMode); // Motor yönünü belirler
    // intakeMotor.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme
  }

  public void runIntake(double speed) {
    intakeMotor.set(VictorSPXControlMode.PercentOutput, speed);
  }

  public void stopIntake() {
    intakeMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
