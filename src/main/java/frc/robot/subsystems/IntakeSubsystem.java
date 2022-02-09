// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  private final WPI_VictorSPX intakeMotor = new WPI_VictorSPX(IntakeConstants.kIntakeMasterId);
  private final PWMTalonSRX DropMotor = new PWMTalonSRX(IntakeConstants.kIntakeMasterOutput);

  public IntakeSubsystem() {
    // intakeMotor.setNeutralMode(NeutralMode.Brake); // Elektriksel Frenleme
    intakeMotor.configFactoryDefault();
  }

  public void runIntake(double speed) {
    intakeMotor.set(speed);
  }

  public void IntakeOn(double m_speed) {
    DropMotor.set(m_speed);
  }

  /*
   * Butonlu Subsystem için örnek fonksiyon
   * InstantCommand()
   * public void IntakeOn(){
   * DropMotor.set(1);
   * }
   */

  public void IntakeOff(double m__speed) {
    DropMotor.set(m__speed);
  }

  public void setIntakeVolts(double volts) {
    intakeMotor.setVoltage(volts);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //addChild("Intake", intakeMotor);
  }
}
