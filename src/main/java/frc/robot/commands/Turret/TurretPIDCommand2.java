// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class TurretPIDCommand2 extends CommandBase {
    /** Creates a new TurretPIDCommand. */
    private TurretSubsystem turret;
    private VisionSubsystem vision;
     
    boolean m_isInterruptible;
    double error;
    double output;
    double outputSum;
    double lastError;
    char shouldTurnSide = 'o';
    char lookingSide;

    public TurretPIDCommand2(TurretSubsystem m_turret,VisionSubsystem m_vision ,boolean isInterruptible) {
        // Use addRequirements() here to declare subsystem dependencies.
        turret = m_turret;
        vision = m_vision;
        m_isInterruptible = isInterruptible;
        addRequirements(m_turret,m_vision);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        output = 0;
        error = 0;
        if (vision.hasTarget()) {
            error =  vision.getYaw();
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
                turret.isAtSetpoint = true;
                shouldTurnSide = 'o';
                // m_turret.isFollowingTarget = false; // might have to remove this
                output = 0;
            } else {
                turret.isAtSetpoint = false;
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
        turret.setTurretVolts(output);
        lastError = error;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        turret.setTurretVolts(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_isInterruptible && turret.isAtSetpoint;
    }
}
