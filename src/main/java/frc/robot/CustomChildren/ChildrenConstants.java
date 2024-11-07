package frc.robot.CustomChildren;

import com.revrobotics.CANSparkLowLevel.MotorType;

public class ChildrenConstants {
    public static final class CustomSpark {
        public static final MotorType defaultMotorType = MotorType.kBrushless;
        public static final double defaultMaxRPM = 5000d;
        public static final double defaultConversionFactor = 1d;

        //TODO: Test for good values for these
        public static final double percentageSetLambda = 5d;
        public static final double rpmSetLambda = 10d;
    }

    public static final class CustomSparkGroup {
        //Length is 32. Probably won't ever have that many motors in a group.
        public static final double[] percentageMaxesArray = {1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d};
    }
}
