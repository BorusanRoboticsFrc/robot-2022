// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class ClimbSubsystem extends SubsystemBase {
  /** Creates a new ClimbSubsystem. */
  VictorSPX leftClimb = new VictorSPX(ClimbConstants.kLeftClimbMasterId);
  VictorSPX rightClimb = new VictorSPX(ClimbConstants.kRightClimbMasterId);
  VictorSPX leftWindow = new VictorSPX(ClimbConstants.kLeftWindowMasterId);
  VictorSPX rightWindow = new VictorSPX(ClimbConstants.kRightWindowMasterId);

  // PWM Output
  // PWMVictorSPX pwmLeftWindow = new PWMVictorSPX(ClimbConstants.kLeftWindowMasterOutput);
  // PWMVictorSPX pwmRightWindow = new PWMVictorSPX(ClimbConstants.kRightWindowMasterOutput);

  public ClimbSubsystem() {
    leftClimb.setInverted(ClimbConstants.kClimbInvertedMode1); // Motor yönünü belirler
    // leftClimb.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme

    rightClimb.setInverted(ClimbConstants.kClimbInvertedMode2); // Motor yönünü belirler
    // rightClimb.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme

    leftWindow.setInverted(ClimbConstants.kClimbInvertedMode3); // Motor yönünü belirler
    // leftWindow.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme

    rightWindow.setInverted(ClimbConstants.kClimbInvertedMode4); // Motor yönünü belirler
    // rightWindow.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme
  }

  public void runleftClimb(double m_speed) {
    leftClimb.set(ControlMode.PercentOutput, m_speed);
  }

  public void runrightClimb(double m_speed) {
    rightClimb.set(ControlMode.PercentOutput, m_speed);
  }

  public void runleftWindow(double m_speed) {
    leftWindow.set(ControlMode.PercentOutput, m_speed);
    //PWM Output
    //leftWindow.set(m_speed);
  }

  public void runrightWindow(double m_speed) {
    rightWindow.set(ControlMode.PercentOutput, m_speed);
    //PWM Output
    //rightWindow.set(m_speed);
  }

  public void stopleftClimb() {
    leftClimb.set(ControlMode.PercentOutput, 0);
  }

  public void stoprightClimb() {
    rightClimb.set(ControlMode.PercentOutput, 0);
  }

  public void stopleftWindow() {
    leftWindow.set(ControlMode.PercentOutput, 0);
    //PWM Output
    //leftWindow.set(0);    
  }

  public void stoprightWindow() {
    rightWindow.set(ControlMode.PercentOutput, 0);
    //PWM Output
    //rightWindow.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
