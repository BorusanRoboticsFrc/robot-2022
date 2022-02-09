// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FunnelConstants;

public class FunnelSubsystem extends SubsystemBase {
  /** Creates a new FunnelSubsystem. */
  private final WPI_VictorSPX funnelMotor = new WPI_VictorSPX(FunnelConstants.kFunnelMasterId);

  public FunnelSubsystem() {
    //funnelMotor.setInverted(FunnelConstants.kFunnelInvertedMode); // Motor yönünü belirler
    // funnelMotor.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme
    funnelMotor.configFactoryDefault();
  }

  public void runFunnel(double speed) {
    funnelMotor.set(speed);
  }

  public void setFunnelVolts(double volts) {
    funnelMotor.setVoltage(volts);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //addChild("Funnel", funnelMotor);
    //SmartDashboard.putNumber("Funnel Voltaj", funnelMotor.getBusVoltage());
    //SmartDashboard.putNumber("Funnel Motor Speed", funnelMotor.getMotorOutputPercent());
  }
}
