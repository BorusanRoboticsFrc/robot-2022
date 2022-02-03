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
    }

    // Intake
    public static final class IntakeConstants {
        public static final int kIntakeMasterId = 7; // Top Alma
        public static final boolean kIntakeInvertedMode = true; // Motor yönünü belirler True - False
    }

    // Funnel
    public static final class FunnelConstants {
        public static final int kFunnelMasterId = 8; // Top Taşıma
        public static final boolean kFunnelInvertedMode = true; // Motor yönünü belirler True - False
    }

    // Turret
    public static final class TurretConstants {
        public static final int kTurretMasterId = 9; // Top Yönlendirme
        public static final boolean kTurretInvertedMode = true; // Motor yönünü belirler True - False
    }

    // Shooter
    public static final class ShooterConstants {
        public static final int kShooterMasterId = 10; // Top Fırlatma
        public static final boolean kShooterInvertedMode = true; // Motor yönünü belirler True - False
    }

    // Climb
    public static final class ClimbConstants {
        public static final int kLeftClimbMasterId = 5; // Sol Tırmanma
        public static final int kRightClimbMasterId = 6; // Sağ Tırmanma
        public static final int kLeftWindowMasterId = 11; // Tırmanma Sol Silecek Motoru
        public static final int kRightWindowMasterId = 12; // Tırmanma Sağ Silecek Motoru
        // Climb PWM
        public static final int kLeftWindowMasterOutput = 2; // Tırmanma Silecek Motoru
        public static final int kRightWindowMasterOutput = 3; // Tırmanma Silecek Motoru

        public static final boolean kClimbInvertedMode1 = true; // Motor yönünü belirler True - False
        public static final boolean kClimbInvertedMode2= true; // Motor yönünü belirler True - False
        public static final boolean kClimbInvertedMode3 = true; // Motor yönünü belirler True - False
        public static final boolean kClimbInvertedMode4 = true; // Motor yönünü belirler True - False

    }
}
