// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.Robot;
import frc.robot.subsystems.TurretSubsystem;

public class TurretPIDCommand extends CommandBase {
    /** Creates a new TurretPIDCommand. */
    private TurretSubsystem m_turret;

    boolean m_isInterruptible;
    double error;
    double output;
    double outputSum;
    double lastError;
    char shouldTurnSide = 'o';
    char lookingSide;

    public TurretPIDCommand(TurretSubsystem turret, boolean isInterruptible) {
        // Use addRequirements() here to declare subsystem dependencies.
        m_turret = turret;
        m_isInterruptible = isInterruptible;
        addRequirements(m_turret);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        output = 0;
        m_turret.isFollowingTarget = true;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        output = 0;
        error = 0;
        if (Robot.isValidAngle()) {
            error = Robot.getVisionYawAngle();
            output = (TurretConstants.kP * error + (TurretConstants.kD * (error - lastError)));
            if (error >= 15) {
                output = 5;
            } else if (error <= -15) {
                output = -5;
            }

            shouldTurnSide = error > 0 ? 'r' : 'l';

            if (output > 12) {
                output = 12;
            } else if (output < -12) {
                output = -12;
            }

            if (error >= -2 && error <= 2) {
                m_turret.isAtSetpoint = true;
                shouldTurnSide = 'o';
                // m_turret.isFollowingTarget = false; // might have to remove this
                output = 0;
            } else {
                m_turret.isAtSetpoint = false;
            }
            if (shouldTurnSide == 'o') {
                output = 0;
            }
            outputSum += output;
            if (outputSum > 0) {
                lookingSide = 'r';
            } else {
                lookingSide = 'l';
            }

        } else {
            output = 0;
        }
        if (0 < output && 2 > output) {
            output += 0.75;
        } else if (0 > output && -2 < output) {
            output -= 0.75;
        }
        System.out.println("Output : " + output);
        m_turret.setTurretVolts(output);
        lastError = error;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_turret.setTurretVolts(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_isInterruptible && m_turret.isAtSetpoint;
    }
}
