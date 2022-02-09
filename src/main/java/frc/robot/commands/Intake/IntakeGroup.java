// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Funnel.RunFunnel;
import frc.robot.subsystems.FunnelSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeGroup extends ParallelCommandGroup {
  /** Creates a new IntakeGroup. */
  public IntakeGroup(IntakeSubsystem m_intake,FunnelSubsystem m_funnel,double speed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new RunIntake(m_intake, speed),new RunFunnel(m_funnel, speed));
  }
}
   