package frc.robot.CoreValues;

public final class Measurements {
    public static final class Constants {
        public static final double driveWheelDiameter = 0.1016d;

        public static final double leftToRightLengthMetres = 0.685d;
        public static final double upToDownDistanceMetres = 0.685d;

        public static final double[] leftUpModulePos = new double[]{-leftToRightLengthMetres / 2d, upToDownDistanceMetres / 2d};
        public static final double[] leftDownModulePos = new double[]{-leftToRightLengthMetres / 2d, -upToDownDistanceMetres / 2d};
        public static final double[] rightUpModulePos = new double[]{leftToRightLengthMetres / 2d, upToDownDistanceMetres / 2d};
        public static final double[] rightDownModulePos = new double[]{leftToRightLengthMetres / 2d, -upToDownDistanceMetres / 2d};

        public static final double maxTranslationalSpeed = 3.420296449459403d;
        public static final double maxAngularSpeed = 2 * Math.PI;
    }

    public static class Dynamics {

    }
}
