// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new VisionSubsystem. */
  PhotonCamera borusancamera;
  PhotonPipelineResult result;
  NetworkTableEntry yawResult;
  NetworkTableInstance NTmain;
  NetworkTable nt;

  public VisionSubsystem() {
    borusancamera = new PhotonCamera("borusanvision");
    NTmain = NetworkTableInstance.getDefault();
    nt = NTmain.getTable("borusanphotonvision").getSubTable("borusanvision");
  }

  public double getYaw() {
    return nt.getEntry("targetYaw").getDouble(Double.NaN);
  }

  public double getPitch() {
    return nt.getEntry("targetPitch").getDouble(Double.NaN);
  }

  public double getArea() {
    return nt.getEntry("targetArea").getDouble(Double.NaN);
  }
  
  public double getSkew() {
    return nt.getEntry("targetSkew").getDouble(Double.NaN);
  }

  public boolean hasTarget() {
    return nt.getEntry("hasTarget").getBoolean(false);
  }

  public void setDriveMode(boolean mode) {
    borusancamera.setDriverMode(mode);
  }

  @Override
  public void periodic() {
    result = borusancamera.getLatestResult();
    // This method will be called once per scheduler run
    //Sallamasyon
    SmartDashboard.putNumber("Koordinat:X", getYaw());
    SmartDashboard.putNumber("Koordinat:Y", getPitch());
    SmartDashboard.putNumber("Koordinat:Z", getArea());
    SmartDashboard.putNumber("Koordinat:K", getSkew());

  }
}
