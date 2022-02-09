// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotState;
import frc.robot.commands.Trigger.TriggerCG;
import frc.robot.commands.Turret.TurretPIDCommand;
import frc.robot.subsystems.FunnelSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TriggerSubsystem;
import frc.robot.subsystems.TurretSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoShootCommand extends SequentialCommandGroup {
  /** Creates a new AutoShootCommand. */
  TurretSubsystem turret;
  ShooterSubsystem shooter;
  FunnelSubsystem funnel;
  TriggerSubsystem trigger;

  public AutoShootCommand(TurretSubsystem m_turret, FunnelSubsystem m_funnel, TriggerSubsystem m_trigger, ShooterSubsystem m_shooter) {
    Robot.robotState = RobotState.SHOOT;
    turret = m_turret;
    funnel = m_funnel;
    trigger = m_trigger;
    shooter = m_shooter;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new TurretPIDCommand(m_turret, false)
        .alongWith(new SetShooterRPMPF(3500, shooter, false)
        .alongWith(new TriggerCG(m_funnel, m_trigger)
        )
      )
    );
  }
}
