// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Funnel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FunnelSubsystem;

public class RunFunnel extends CommandBase {
  /** Creates a new RunFunnel. */
  FunnelSubsystem funnel;
  double speed;
  public RunFunnel(FunnelSubsystem m_funnel, double m_speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.funnel=m_funnel;
    this.speed=m_speed;
    addRequirements(funnel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    funnel.runFunnel(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    funnel.stopFunnel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
