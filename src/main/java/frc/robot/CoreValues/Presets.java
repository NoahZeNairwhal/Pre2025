package frc.robot.CoreValues;

public final class Presets {
    public static final class Constants {
        public static final class Intake {
            public static final double intaking = 0.4d;
            public static final double outtaking = -intaking;
            public static final double sourceTop = -0.4;
            public static final double sourceBack = 0.4;
            public static final double sourceGround = 0.4;
        }

        public static final class Middle {
            public static final double intaking = 0.15d;
            public static final double outtaking = -intaking;
            public static final double sourceIntake = -0.3;
        }

        public static final class Launcher {
            public static final double intaking = -0.3d;
            public static final double outtaking = intaking;

            public static final double ampLaunch = 0.11;
            public static final double sourceIntake = -0.3;
            public static final double rapidLaunch = 0.35;//0.64;
        }

        public static final class Hooks {
            public static final double extending = 1d;
            public static final double retracting = -extending;
        }

        public static final class ArmAngler {
            public static final double ampAngle = 65.0;
            public static final double sourceAngle = 72.0;//66.0;//73.1;
            public static final double rapidAngle = 60.0;//65.4;
        }
    }

    public static class Dynamics {
        
    }
}
