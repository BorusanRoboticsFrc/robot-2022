// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FunnelConstants;

public class FunnelSubsystem extends SubsystemBase {
  /** Creates a new FunnelSubsystem. */
  VictorSPX funnelMotor = new VictorSPX(FunnelConstants.kFunnelMasterId);

  public FunnelSubsystem() {
    funnelMotor.setInverted(FunnelConstants.kFunnelInvertedMode); // Motor yönünü belirler
    // funnelMotor.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme
  }

  public void runFunnel(double speed) {
    funnelMotor.set(VictorSPXControlMode.PercentOutput, speed);
  }

  public void stopFunnel() {
    funnelMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Funnel Voltaj", funnelMotor.getBusVoltage());
    SmartDashboard.putNumber("Funnel Motor Speed", funnelMotor.getMotorOutputPercent());
  }
}
