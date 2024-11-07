package frc.robot.CoreValues;

public final class Conversions {
    public static final class Constants {
        //Drive Motors
        public static final double driveGearRatio = 1d / 6.12d;
        public static final double driveMetresPerRotation = driveGearRatio * Measurements.Constants.driveWheelDiameter * Math.PI;

        //Turn Motors
        public static final double turnGearRatio = 1d / 1d;
        public static final double turnRadsPerRotation = turnGearRatio * 2 * Math.PI;
        public static final double turnDegreesPerRotation = turnRadsPerRotation * 180 / Math.PI;

        //Turn Encoders
        public static final double turnEncoderRatio = 1d / 1d;
        public static final double turnEncoderRadsPerRotation = turnEncoderRatio * 2 * Math.PI;
        public static final double turnEncoderDegreesPerRotation = turnEncoderRadsPerRotation * 180 / Math.PI;

        //Arm Angle Encoder
        public static final double armAngleEncoderRatio = 1d / 1d;
        public static final double armAngleEncoderRadsPerRotation = armAngleEncoderRatio * 2 * Math.PI;
        public static final double armAngleEncoderDegreesPerRotation = armAngleEncoderRadsPerRotation * 180 / Math.PI;

        //Hook Motors
        public static final double hookMetresPerRotation = 1d;
    }

    public static class Dynamics {
        
    }
}
