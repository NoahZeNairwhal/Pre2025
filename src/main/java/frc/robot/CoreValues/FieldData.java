package frc.robot.CoreValues;

public final class FieldData {
    public static final class Constants {
        //Assumes southwest corner from blue alliance pov is (0, 0), with moving north +y and moving east +x
        public static final double speakerX = 2.663;
        public static final double ampX = 0.0;
        public static final double trapX1 = 4.093;
        public static final double trapX2 = 3.740;
        public static final double trapX3 = 4.445;
        public static final double rapidX = 1.235;

        public static final double minAmpAngle = 0.0;
        public static final double maxAmpAngle = 360.0;

        public static final class blueConstants {
            public static final double speakerY = 0.0;
            public static final double ampY = 1.84;
            public static final double trapY1 = 5.275;
            public static final double trapY2 = 4.664;
            public static final double trapY3 = 4.664;
            public static final double rapidY = 5.873;

            public static final double minSpeakerAngle = 0.0;
            public static final double maxSpeakerAngle = 360.0;
            public static final double minTrap1Angle = 0.0;
            public static final double maxTrap1Angle = 360.0;
            public static final double minTrap2Angle = 0.0;
            public static final double maxTrap2Angle = 360.0;
            public static final double minTrap3Angle = 0.0;
            public static final double maxTrap3Angle = 360.0;
            public static final double minRapidAngle = 0.0;
            public static final double maxRapidAngle = 360.0;
        }
        
        public static final class redConstants {
            public static final double speakerY = 16.541;
            public static final double ampY = 14.701;
            public static final double trapY1 = 11.266;
            public static final double trapY2 = 11.877;
            public static final double trapY3 = 11.877;
            public static final double rapidY = 10.719;

            public static final double minSpeakerAngle = 0.0;
            public static final double maxSpeakerAngle = 360.0;
            public static final double minTrap1Angle = 0.0;
            public static final double maxTrap1Angle = 360.0;
            public static final double minTrap2Angle = 0.0;
            public static final double maxTrap2Angle = 360.0;
            public static final double minTrap3Angle = 0.0;
            public static final double maxTrap3Angle = 360.0;
            public static final double minRapidAngle = 0.0;
            public static final double maxRapidAngle = 360.0;
        }
    }

    public static class Dynamics {
        public static double speakerY;
        public static double ampY;
        public static double trapY1;
        public static double trapY2;
        public static double trapY3;
        public static double rapidY;

        public static double minSpeakerAngle;
        public static double maxSpeakerAngle;
        public static double minTrap1Angle;
        public static double maxTrap1Angle;
        public static double minTrap2Angle;
        public static double maxTrap2Angle;
        public static double minTrap3Angle;
        public static double maxTrap3Angle;
        public static double minRapidAngle;
        public static double maxRapidAngle;
    }
}
