package frc.robot.Arm;

import com.revrobotics.RelativeEncoder;

import frc.robot.CoreValues.IDs;
import frc.robot.CoreValues.Inversions;
import frc.robot.CustomChildren.CustomSpark;
import frc.robot.CustomChildren.CustomSparkGroup;

public class Middle {
    public CustomSpark backCustomSpark;
    public CustomSpark frontCustomSpark;

    public CustomSparkGroup sparkGroup;

    public RelativeEncoder backRelativeEncoder;
    public RelativeEncoder frontRelativeEncoder;

    public Middle() {
        backCustomSpark = new CustomSpark(IDs.Constants.backMiddleRoller, Inversions.Constants.backMiddleRoller);
        backRelativeEncoder = backCustomSpark.relativeEncoder;
        frontCustomSpark = new CustomSpark(IDs.Constants.frontMiddleRoller, Inversions.Constants.frontMiddleRoller);
        frontRelativeEncoder = frontCustomSpark.relativeEncoder;

        sparkGroup = new CustomSparkGroup(new CustomSpark[]{backCustomSpark, frontCustomSpark});
    }
}
