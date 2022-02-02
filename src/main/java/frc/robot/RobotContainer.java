// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Auto.Auto1Ball;
import frc.robot.commands.Auto.Auto2BallIntake;
import frc.robot.commands.Auto.AutoCommand;
import frc.robot.commands.Auto.BlindAuto;
import frc.robot.commands.Drive.JoystickDriveCommand;
import frc.robot.commands.Funnel.RunFunnel;
import frc.robot.commands.Intake.IntakeGroup;
import frc.robot.commands.Intake.RunIntake;
import frc.robot.commands.Shooter.RunShooter;
import frc.robot.commands.Turret.AutoAimCommand;
import frc.robot.commands.Turret.RunTurret;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FunnelSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  //Joystick
  public Joystick m_driverController = new Joystick(JoystickConstants.F310);
  public Joystick m_operatorController = new Joystick(JoystickConstants.Panel);

  //Subsystems
  public final DriveSubsystem m_robotDrive = new DriveSubsystem();
  public final IntakeSubsystem m_intake = new IntakeSubsystem();
  public final FunnelSubsystem m_funnel = new FunnelSubsystem();
  public final TurretSubsystem m_turret = new TurretSubsystem();
  public final ShooterSubsystem m_shooter = new ShooterSubsystem();
  public final ClimbSubsystem m_climb = new ClimbSubsystem();
  public final VisionSubsystem m_vision = new VisionSubsystem();

  //Commands
  BlindAuto blindAuto = new BlindAuto();
  Auto1Ball auto1Ball = new Auto1Ball();
  Auto2BallIntake auto2BallIntake = new Auto2BallIntake();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_robotDrive.setDefaultCommand(new JoystickDriveCommand(m_robotDrive,() -> m_driverController.getRawAxis(1),() -> -m_driverController.getRawAxis(4)));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Vision Kamerasını Drive Kamerasına Çevirmek True - False
    //new JoystickButton(m_driverController,1).whileHeld(new RunCommand(()-> m_vision.setDriveMode(true), m_vision));
    //new JoystickButton(m_driverController,2).whileHeld(new RunCommand(()-> m_vision.setDriveMode(false), m_vision));


    // Top Alma F310
    new JoystickButton(m_driverController, 1).whileHeld(new RunIntake(m_intake,0.8));
    new JoystickButton(m_driverController, 3).whileHeld(new RunIntake(m_intake,-0.8));

    // Top Alma Panel
    new JoystickButton(m_operatorController, 1).whileHeld(new RunIntake(m_intake,0.8));
    new JoystickButton(m_operatorController, 8).whileHeld(new RunIntake(m_intake,-0.8));
  
    // Top Taşıma Panel
    new JoystickButton(m_operatorController, 10).whileHeld(new RunFunnel(m_funnel,0.8));
    new JoystickButton(m_operatorController, 9).whileHeld(new RunFunnel(m_funnel,-0.8));

    // Top Topla & Taşı F310
    new JoystickButton(m_driverController, 6).whileHeld(new IntakeGroup(m_intake,m_funnel,0.4));
    new JoystickButton(m_driverController, 5).whileHeld(new IntakeGroup(m_intake,m_funnel,-0.4));

    // Manuel Kafa Dönme
    new POVButton(m_driverController, 270).whileHeld(new RunTurret(m_turret,0.25));
    new POVButton(m_driverController, 90).whileHeld(new RunTurret(m_turret,-0.25));

    // Otomatik Aim F310
    new JoystickButton(m_driverController, 12).whileHeld(new AutoAimCommand());

    // Manuel Top Atma Panel
    new JoystickButton(m_operatorController, 12).whileHeld(new RunShooter(m_shooter,1));

    // Otomatik Top Atma Panel
    new JoystickButton(m_driverController, 2).whileHeld(new AutoCommand());
    
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
        //return null;
      case 2:
        //return auto1Ball;
        return auto2BallIntake;
      default:
        return blindAuto;
    }
  }
}
