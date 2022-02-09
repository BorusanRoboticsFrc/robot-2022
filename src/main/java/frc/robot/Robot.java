// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  NetworkTableInstance photon = NetworkTableInstance.create();
  public static RobotState robotState;
  public static NetworkTableEntry angle;
  public static NetworkTableEntry validAngle;
  public static NetworkTableInstance inst;
  NetworkTable table = photon.getTable("borusanphotonvision").getSubTable("borusanvision");

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  public Alliance allianceColor;
  public int dsLocation;

  public static SendableChooser<Integer> autoChooser = new SendableChooser<>();
  PowerDistribution examplePD = new PowerDistribution();
  double voltage;
  double current;
  double power;
  double energy;
  double temp;
  double shooter;
  double turret;
  double funnel;
  double intake;
  double climb1;
  double climb2;

  // voltaj
  public double getVoltage() {
    voltage = examplePD.getVoltage();
    return voltage;
  }

  // toplam akım
  public double getTotalCurrent() {
    current = examplePD.getTotalCurrent();
    return current;
  }

  // toplam güç
  public double getTotalPower() {
    power = examplePD.getTotalPower();
    return power;
  }

  // toplam enerji
  public double getTotalEnergy() {
    energy = examplePD.getTotalEnergy();
    return energy;
  }

  // sıcaklık
  public double getTemperature() {
    temp = examplePD.getTemperature();
    return temp;
  }

  // shooter akım
  public double getVictorShooter() {
    shooter = examplePD.getCurrent(0);
    return shooter;
  }

  // turret akım
  public double getVictorTurret() {
    turret = examplePD.getCurrent(1);
    return turret;
  }

  // funnel akım
  public double getVictorFunnel() {
    funnel = examplePD.getCurrent(2);
    return funnel;
  }

  // intake akım
  public double getVictorIntake() {
    intake = examplePD.getCurrent(3);
    return intake;
  }

  // climb2 akım
  public double getVictorClimb2() {
    climb2 = examplePD.getCurrent(4);
    return climb2;
  }

  // climb1 akım
  public double getVictorClimb() {
    climb1 = examplePD.getCurrent(5);
    return climb1;
  }

  public void displayMatchInfo() {
    SmartDashboard.putString("Event Name", DriverStation.getEventName());
    SmartDashboard.putNumber("Match Number", DriverStation.getMatchNumber());
    SmartDashboard.putNumber("Time Remaining", DriverStation.getMatchTime());
  }

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.

    photon.startClient("10.49.72.12");
    angle = table.getEntry("targetYaw");
    validAngle = table.getEntry("hasTarget");

    this.allianceColor = DriverStation.getAlliance();
    this.dsLocation = DriverStation.getLocation();

    m_robotContainer = new RobotContainer();
    Shuffleboard.getTab("PDP").add(examplePD);
    Shuffleboard.getTab("PDP").add("VOLTAJ", getVoltage());
    Shuffleboard.getTab("PDP").add("TOPLAM AKIM", getTotalCurrent());
    Shuffleboard.getTab("PDP").add("TOPLAM GÜÇ", getTotalPower());
    Shuffleboard.getTab("PDP").add("TOPLAM ENERJI", getTotalEnergy());
    Shuffleboard.getTab("PDP").add("SICAKLIK", getTemperature());
    Shuffleboard.getTab("PDP").add("SHOOTER AKIM", getVictorShooter());
    Shuffleboard.getTab("PDP").add("TURRET AKIM", getVictorTurret());
    Shuffleboard.getTab("PDP").add("FUNNEL AKIM", getVictorFunnel());
    Shuffleboard.getTab("PDP").add("INTAKE AKIM", getVictorIntake());
    Shuffleboard.getTab("PDP").add("CLIMB1 AKIM", getVictorClimb());
    Shuffleboard.getTab("PDP").add("CLIMB2 AKIM", getVictorClimb2());

    autoChooser.setDefaultOption("Blind Auto", 0);
    autoChooser.addOption("1 Ball Auto / Vision", 1);
    autoChooser.addOption("2 Ball Auto / Vision + Intake", 2);
    SmartDashboard.putData(autoChooser);
    robotState = RobotState.IDLE;

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand(autoChooser.getSelected());
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  public static double getVisionYawAngle() {
    return angle.getDouble(0);
  }

  public static boolean isValidAngle() {

    return validAngle.getBoolean(false);
  }
}