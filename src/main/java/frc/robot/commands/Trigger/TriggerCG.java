// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Trigger;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Funnel.RunFunnel;
import frc.robot.subsystems.FunnelSubsystem;
import frc.robot.subsystems.TriggerSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TriggerCG extends ParallelCommandGroup {
  /** Creates a new TriggerCG. */
  FunnelSubsystem funnel;
  TriggerSubsystem trigger;

  public TriggerCG(FunnelSubsystem m_funnel, TriggerSubsystem m_trigger) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new RunFunnel(m_funnel, 0.6), new RunTrigger(m_trigger, 0.6));
  }
}
