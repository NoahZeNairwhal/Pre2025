package frc.robot.Arm;

import com.revrobotics.RelativeEncoder;

import frc.robot.CoreValues.IDs;
import frc.robot.CoreValues.Inversions;
import frc.robot.CustomChildren.CustomSpark;
import frc.robot.CustomChildren.CustomSparkGroup;

public class Launcher {
    public CustomSpark backCustomSpark;
    public CustomSpark frontCustomSpark;

    public CustomSparkGroup sparkGroup;

    public RelativeEncoder backRelativeEncoder;
    public RelativeEncoder frontRelativeEncoder;

    public Launcher() {
        backCustomSpark = new CustomSpark(IDs.Constants.backLauncher, Inversions.Constants.backLauncher);
        backRelativeEncoder = backCustomSpark.relativeEncoder;
        frontCustomSpark = new CustomSpark(IDs.Constants.frontLauncher, Inversions.Constants.frontLauncher);
        frontRelativeEncoder = frontCustomSpark.relativeEncoder;

        sparkGroup = new CustomSparkGroup(new CustomSpark[]{backCustomSpark, frontCustomSpark});
    }
}
