package frc.robot.CoreValues;

import edu.wpi.first.wpilibj.PS4Controller;

public final class Controllers {
    public static final class Constants {
        public static final int driveControllerPort = 0;
        public static final int armControllerPort = 1;

        public static final double driveControllerDeadband = 0.15;
        public static final double armControllerDeadband = 0.15;
    }

    public static class Dynamics {
        public static PS4Controller driveController;
        public static PS4Controller armController;
    }
}
