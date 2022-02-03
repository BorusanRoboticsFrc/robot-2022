// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.ADIS16448_IMU.CalibrationTime;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
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
  public NetworkTableInstance photon = NetworkTableInstance.create();
  public static RobotState robotState;
  public static NetworkTableEntry angle;
  public static NetworkTableEntry validAngle;
  public static NetworkTableInstance inst;
  public NetworkTable table = photon.getTable("photonvision").getSubTable("microsoftlifecam");
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  public static RobotState state = new RobotState();
  public static int totalMatchTime = 150;
  public Alliance allianceColor;
  public int dsLocation;
  public static SendableChooser<Integer> autoChooser = new SendableChooser<>();

  private static final String kYawDefault = "Z-Axis";
  private static final String kYawXAxis = "X-Axis";
  private static final String kYawYAxis = "Y-Axis";
  private String m_yawSelected;
  private ADIS16448_IMU.IMUAxis m_yawActiveAxis;
  private final SendableChooser<String> m_yawChooser = new SendableChooser<>();

  private boolean m_runCal = false;
  private boolean m_configCal = false;
  private boolean m_reset = false;
  private boolean m_setYawAxis = false;

  private final ADIS16448_IMU m_imu = new ADIS16448_IMU();

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
    photon.startClient("10.49.72.10");
    angle = table.getEntry("targetYaw");
    validAngle = table.getEntry("hasTarget");

    this.allianceColor = DriverStation.getAlliance();
    this.dsLocation = DriverStation.getLocation();
    m_robotContainer = new RobotContainer();

    autoChooser.setDefaultOption("Blind Auto", 0);
    autoChooser.addOption("1 Ball Auto / Vision", 1);
    autoChooser.addOption("2 Ball Auto / Vision + Intake", 2);
    SmartDashboard.putData(autoChooser);

    m_yawChooser.setDefaultOption("Z-Axis", kYawDefault);
    m_yawChooser.addOption("X-Axis", kYawXAxis);
    m_yawChooser.addOption("Y-Axis", kYawYAxis);
    SmartDashboard.putData("IMUYawAxis", m_yawChooser);

    SmartDashboard.putBoolean("RunCal", false);
    SmartDashboard.putBoolean("ConfigCal", false);
    SmartDashboard.putBoolean("Reset", false);
    SmartDashboard.putBoolean("SetYawAxis", false);
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
    SmartDashboard.putNumber("YawAngle", m_imu.getAngle());
    SmartDashboard.putNumber("XCompAngle", m_imu.getXComplementaryAngle());
    SmartDashboard.putNumber("YCompAngle", m_imu.getYComplementaryAngle());
    m_runCal = SmartDashboard.getBoolean("RunCal", false);
    m_configCal = SmartDashboard.getBoolean("ConfigCal", false);
    m_reset = SmartDashboard.getBoolean("Reset", false);
    m_setYawAxis = SmartDashboard.getBoolean("SetYawAxis", false);
    m_yawSelected = m_yawChooser.getSelected();

    // Set IMU settings
    if (m_configCal) {
      m_imu.configCalTime(CalibrationTime._8s);
      m_configCal = SmartDashboard.putBoolean("ConfigCal", false);
    }
    if (m_reset) {
      m_imu.reset();
      m_reset = SmartDashboard.putBoolean("Reset", false);
    }
    if (m_runCal) {
      m_imu.calibrate();
      m_runCal = SmartDashboard.putBoolean("RunCal", false);
    }
    
    // Read the desired yaw axis from the dashboard
    if (m_yawSelected == "X-Axis") {
      m_yawActiveAxis = ADIS16448_IMU.IMUAxis.kX;
    }
    else if (m_yawSelected == "Y-Axis") {
      m_yawActiveAxis = ADIS16448_IMU.IMUAxis.kY;
    }
    else {
      m_yawActiveAxis = ADIS16448_IMU.IMUAxis.kZ;
    }
    // Set the desired yaw axis from the dashboard
    if (m_setYawAxis) {
      m_imu.setYawAxis(m_yawActiveAxis);
      m_setYawAxis = SmartDashboard.putBoolean("SetYawAxis", false);
    }
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    state.update(RobotState.State.DISABLED);
    totalMatchTime = 150;
  }

  @Override
  public void disabledPeriodic() {}

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand(autoChooser.getSelected());
    totalMatchTime = 15;
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    totalMatchTime = 135;
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    state.update(RobotState.State.ENABLED);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  public static double getVisionYawAngle() {
    return angle.getDouble(0);
  }

  public static boolean isValidAngle() {

    return validAngle.getBoolean(false);
  }
}