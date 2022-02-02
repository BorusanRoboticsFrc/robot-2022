// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class LeftClimb extends CommandBase {
  /** Creates a new LeftClimb. */
  ClimbSubsystem climb;
  double speed;
  public LeftClimb(ClimbSubsystem m_climb,double m_speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climb=m_climb;
    this.speed=m_speed;
    addRequirements(climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climb.runleftClimb(speed);
    climb.runleftWindow(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climb.runleftClimb(0);
    climb.runleftWindow(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
