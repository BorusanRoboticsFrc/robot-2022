// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class ClimbSubsystem extends SubsystemBase {
  /** Creates a new ClimbSubsystem. */
  CANSparkMax leftClimb = new CANSparkMax(ClimbConstants.kLeftClimbMasterId, MotorType.kBrushed);
  CANSparkMax rightClimb = new CANSparkMax(ClimbConstants.kRightClimbMasterId, MotorType.kBrushed);
  //VictorSPX leftWindow = new VictorSPX(ClimbConstants.kLeftWindowMasterId);
  //VictorSPX rightWindow = new VictorSPX(ClimbConstants.kRightWindowMasterId);

  // PWM Output
  PWMVictorSPX pwmLeftWindow = new PWMVictorSPX(ClimbConstants.kLeftWindowMasterOutput);
  PWMVictorSPX pwmRightWindow = new PWMVictorSPX(ClimbConstants.kRightWindowMasterOutput);

  public ClimbSubsystem() {
    leftClimb.setInverted(ClimbConstants.kClimbInvertedMode1); // Motor yönünü belirler
    // leftClimb.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme

    rightClimb.setInverted(ClimbConstants.kClimbInvertedMode2); // Motor yönünü belirler
    // rightClimb.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme

    //leftWindow.setInverted(ClimbConstants.kClimbInvertedMode3); // Motor yönünü belirler
    // leftWindow.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme

    //rightWindow.setInverted(ClimbConstants.kClimbInvertedMode4); // Motor yönünü belirler
    // rightWindow.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme
  }

  public void runleftClimb(double speed) {
    leftClimb.set(speed);
  }
  
  /*
  public void setVoltageleftClimb(double voltage) {
    leftClimb.setVoltage(voltage);
  }
  */

  public void runrightClimb(double speed) {
    rightClimb.set(speed);
  }

  public void runleftWindow(double speed) {
    //leftWindow.set(ControlMode.PercentOutput, m_speed);
    //PWM Output
    pwmLeftWindow.set(speed);
  }

  public void runrightWindow(double speed) {
    //rightWindow.set(ControlMode.PercentOutput, m_speed);
    //PWM Output
    pwmRightWindow.set(speed);
  }

  public void stopMotor(){
    leftClimb.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
