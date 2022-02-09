// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GyroImuSubsystem extends SubsystemBase {
  /** Creates a new GyroImuSubsystem. */
  public static final ADIS16448_IMU imu = new ADIS16448_IMU();
  public GyroImuSubsystem() {}

  public void calibrate(){
    imu.calibrate();
  }

  public double getAngle(){
    return imu.getAngle();
  }

  public double getRate(){
    return imu.getRate();
  }

  public double getRateX(){
    return imu.getGyroRateX();
  }

  public void zero(){
    imu.reset();
  }

  @Override
  public void periodic() {
      // This method will be called once per scheduler run
  }
}
