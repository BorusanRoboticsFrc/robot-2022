// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Joystick
    public static final class JoystickConstants {
        public static final int F310 = 0; // Joystick
        public static final int Panel = 1; // Driver Station Button
    }

    // Drive
    public static final class DriveConstants {
        public static final int kLeftDriveMasterOutput = 0; // Sase Sol
        public static final int kRightDriveMasterOutput = 1; // Sase Sağ
        public static final boolean kLeftDriveInvertedMode = true; // Motor yönünü belirler True - False
        public static final boolean kRightDriveInvertedMode = true; // Motor yönünü belirler True - False
        
        public static final boolean kGyroReversed = false;

        public static final double kTurnP = 1;
        public static final double kTurnI = 0;
        public static final double kTurnD = 0;

        public static final double kTurnToleranceDeg = 5;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second

        public static final double kMaxTurnRateDegPerS = 100;
        public static final double kMaxTurnAccelerationDegPerSSquared = 300;
    }

    // Intake
    public static final class IntakeConstants {
        public static final int kIntakeMasterId = 7; // Top Alma
        public static final int kIntakeMasterOutput=2;
        public static final boolean kIntakeInvertedMode = true; // Motor yönünü belirler True - False
    }

    // Funnel
    public static final class FunnelConstants {
        public static final int kFunnelMasterId = 8; // Top Taşıma
        public static final boolean kFunnelInvertedMode = true; // Motor yönünü belirler True - False
    }

    // Triger
    public static final class TriggerConstants {
        public static final int kTriggerMasterOutput = 3;
    }

    // Turret
    public static final class TurretConstants {
        public static final byte kTurretMasterId = 9; // Top Yönlendirme
        public static final boolean kIsMotorReversed = true; // Motor yönünü belirler True - False

        public static final byte kTurretEncoderA = 2;
        public static final byte kTurretEncoderB = 3;
        public static final boolean kIsEncoderReversed = false;
        public static final double kP = 0.2; // 0.23
        public static final double kI = 0.000;
        public static final double kD = 1; // 0.28

        public static final double kS = 0.000;
        public static final double kV = 0.000;
        public static final double kA = 0.000;

        public static final int kTurretEncoderPPR = 7; // AM-3132
        public static final byte kToleranceInDegrees = 0;
    }

    // Shooter
    public static final class ShooterConstants {
        public static final int kShooterMasterId = 10; // Top Fırlatma
        public static final boolean kShooterInvertedMode = true; // Motor yönünü belirler True - False

        public static final byte kShooterEncoderA = 0;
        public static final byte kShooterEncoderB = 1;
        public static final boolean kShooterEncoderIsReversed = false;
        public static final double kShootP = 0.025; // 0.04
        public static final double kShootI = 0.000;
        public static final double kShootD = 0.000;

        public static final double kS = 0.656;
        public static final double kV = 0.00202;
        public static final double kA = 0.000494;

        public static final int kShooterEncoderPPR = 2048; // AMT-103
        public static final byte kToleranceInDegrees = 0;
    }

    // Climb
    public static final class ClimbConstants {
        public static final int kLeftClimbMasterId = 5; // Sol Tırmanma
        public static final int kRightClimbMasterId = 6; // Sağ Tırmanma
        // Climb PWM
        public static final int kLeftWindowMasterOutput = 4; // Tırmanma Silecek Motoru
        public static final int kRightWindowMasterOutput = 5; // Tırmanma Silecek Motoru

        public static final boolean kClimbInvertedMode1 = true; // Motor yönünü belirler True - False
        public static final boolean kClimbInvertedMode2 = true; // Motor yönünü belirler True - False
        public static final boolean kClimbInvertedMode3 = true; // Motor yönünü belirler True - False
        public static final boolean kClimbInvertedMode4 = true; // Motor yönünü belirler True - False

    }
}
