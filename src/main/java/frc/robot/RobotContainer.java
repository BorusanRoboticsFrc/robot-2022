// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Auto.Auto1Ball;
import frc.robot.commands.Auto.Auto2BallIntake;
import frc.robot.commands.Auto.BlindAuto;
import frc.robot.commands.Drive.JoystickDriveCommand;
import frc.robot.commands.Drive.TurnToAngle;
import frc.robot.commands.Drive.TurnToAngleProfiled;
import frc.robot.commands.Funnel.RunFunnel;
import frc.robot.commands.Intake.IntakeGroup;
import frc.robot.commands.Intake.RunIntake;
import frc.robot.commands.Shooter.AutoShootCommand;
import frc.robot.commands.Shooter.RunShooter;
import frc.robot.commands.Trigger.RunTrigger;
import frc.robot.commands.Turret.TurretGroupCommand;
import frc.robot.commands.Turret.TurretPIDCommand2;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FunnelSubsystem;
import frc.robot.subsystems.GyroImuSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TriggerSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Joystick
  public final Joystick m_driverController = new Joystick(JoystickConstants.F310);
  public final Joystick m_operatorController = new Joystick(JoystickConstants.Panel);

  // Subsystems
  public final DriveSubsystem m_robotDrive = new DriveSubsystem();
  public final IntakeSubsystem m_intake = new IntakeSubsystem();
  public final FunnelSubsystem m_funnel = new FunnelSubsystem();
  public final TriggerSubsystem m_trigger = new TriggerSubsystem();
  public final TurretSubsystem m_turret = new TurretSubsystem();
  public final ShooterSubsystem m_shooter = new ShooterSubsystem();
  public final ClimbSubsystem m_climb = new ClimbSubsystem();
  public final VisionSubsystem m_vision = new VisionSubsystem();
  public final GyroImuSubsystem m_gyro = new GyroImuSubsystem();

  // Commands
  BlindAuto blindAuto = new BlindAuto();
  Auto1Ball auto1Ball = new Auto1Ball(m_robotDrive, m_gyro);
  Auto2BallIntake auto2BallIntake = new Auto2BallIntake();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Joystick
    // m_robotDrive.setDefaultCommand(new JoystickDriveCommand(m_robotDrive, () ->
    // -m_driverController.getRawAxis(2),
    // () -> m_driverController.getRawAxis(1)));

    // F310
    // m_robotDrive.setDefaultCommand(new JoystickDriveCommand(m_robotDrive, () ->
    // -m_driverController.getRawAxis(1),
    // () -> m_driverController.getRawAxis(4)));

    // SIMULATION
    m_robotDrive.setDefaultCommand(new JoystickDriveCommand(m_robotDrive, () -> -m_driverController.getRawAxis(1),
        () -> m_driverController.getRawAxis(4)));

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@linkxx
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Butona Subsystem Vermek
    // new JoystickButton(m_driverController, 5).whileHeld(new
    // InstantCommand(m_intake::IntakeOn, m_intake));

    // Top Topla & Taşı Joystick
    new JoystickButton(m_driverController, 1).whileHeld(new IntakeGroup(m_intake, m_funnel, 1));
    new JoystickButton(m_driverController, 2).whileHeld(new IntakeGroup(m_intake, m_funnel, -1));

    // Top Alma Joystick
    new JoystickButton(m_driverController, 5).whileHeld(new RunIntake(m_intake, 1));
    new JoystickButton(m_driverController, 3).whileHeld(new RunIntake(m_intake, -1));

    // Tetikleyici Joystick
    new JoystickButton(m_driverController, 6).whileHeld(new RunIntake(m_intake, 1));
    new JoystickButton(m_driverController, 4).whileHeld(new RunIntake(m_intake, -1));

    // Otomatik Aim Joystick
    // new JoystickButton(m_driverController, 7).whileHeld(new
    // TurretPIDCommand(m_turret,false));
    new JoystickButton(m_driverController, 7).whileHeld(new TurretPIDCommand2(m_turret, m_vision, false));

    // Manuel Kafa Dönme Joystick
    new JoystickButton(m_driverController, 8).whileHeld(new TurretGroupCommand(m_turret, 0.3));
    new JoystickButton(m_driverController, 9).whileHeld(new TurretGroupCommand(m_turret, -0.3));

    // Vision Kamerasını Drive Kamerasına Çevirmek True - False
    new JoystickButton(m_driverController, 11).whileHeld(new RunCommand(() -> m_vision.setDriveMode(true), m_vision));
    new JoystickButton(m_driverController, 12).whileHeld(new RunCommand(() -> m_vision.setDriveMode(false), m_vision));

    // Top Alma Panel
    new JoystickButton(m_operatorController, 1).whileHeld(new RunIntake(m_intake, 1));
    new JoystickButton(m_operatorController, 8).whileHeld(new RunIntake(m_intake, -1));

    // Top Taşıma Panel
    new JoystickButton(m_operatorController, 10).whileHeld(new RunFunnel(m_funnel, 1));
    new JoystickButton(m_operatorController, 9).whileHeld(new RunFunnel(m_funnel, -1));

    // Kafaya Tetik Panel
    new JoystickButton(m_operatorController, 5).whileHeld(new RunTrigger(m_trigger, 1));
    new JoystickButton(m_operatorController, 7).whileHeld(new RunTrigger(m_trigger, -1));

    // Manuel Top Atma Panel
    new JoystickButton(m_operatorController, 11).whileHeld(new RunShooter(m_shooter, 1));

    // Otomatik Top Atma Panel
    new JoystickButton(m_driverController, 12)
        .whileHeld(new AutoShootCommand(m_turret, m_funnel, m_trigger, m_shooter));

    // Tırmanma

    // Turn to 90 degrees when the 'X' button is pressed, with a 5 second timeout
    new JoystickButton(m_driverController, 2)
        .whenPressed(new TurnToAngle(90, m_robotDrive).withTimeout(5));

    // Turn to -90 degrees with a profile when the Circle button is pressed, with a
    // 5 second timeout
    new JoystickButton(m_driverController, 3)
        .whenPressed(new TurnToAngleProfiled(-90, m_robotDrive).withTimeout(5));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(int auto) {
    // An ExampleCommand will run in autonomous
    switch (auto) {
      case 1:
        return auto1Ball;
      // return null;
      case 2:
        // return auto1Ball;
        return auto2BallIntake;
      default:
        // return blindAuto;
        return auto1Ball;
    }
  }
}
