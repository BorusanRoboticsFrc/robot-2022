// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GyroImuSubsystem;

public class Drive90Command extends CommandBase {
  /** Creates a new Drive90Command. */
  DriveSubsystem drive;
  GyroImuSubsystem gyro;
  double kP = 1;
  public Drive90Command(DriveSubsystem m_drive, GyroImuSubsystem m_gyro) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive,m_gyro);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
        // Find the heading error; setpoint is 90
        double error = 90 - gyro.getAngle();

        // Turns the robot to face the desired direction
        drive.tankDrive(kP * error, kP * error);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
