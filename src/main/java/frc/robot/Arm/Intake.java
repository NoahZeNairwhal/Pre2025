package frc.robot.Arm;

import com.revrobotics.RelativeEncoder;

import frc.robot.CoreValues.IDs;
import frc.robot.CoreValues.Inversions;
import frc.robot.CustomChildren.CustomSpark;
import frc.robot.CustomChildren.CustomSparkGroup;

public class Intake {
    public CustomSpark groundCustomSpark;
    public CustomSpark bottomCustomSpark;
    public CustomSpark topCustomSpark;

    public CustomSparkGroup sparkGroup;

    public RelativeEncoder groundRelativeEncoder;
    public RelativeEncoder bottomRelativeEncoder;
    public RelativeEncoder topRelativeEncoder;

    public Intake() {
        groundCustomSpark = new CustomSpark(IDs.Constants.groundRoller, Inversions.Constants.groundRoller);
        groundRelativeEncoder = groundCustomSpark.relativeEncoder;
        bottomCustomSpark = new CustomSpark(IDs.Constants.bottomIntake, Inversions.Constants.bottomIntake);
        bottomRelativeEncoder = bottomCustomSpark.relativeEncoder;
        topCustomSpark = new CustomSpark(IDs.Constants.topIntake, Inversions.Constants.topIntake);
        topRelativeEncoder = topCustomSpark.relativeEncoder;

        sparkGroup = new CustomSparkGroup(new CustomSpark[]{groundCustomSpark, bottomCustomSpark, topCustomSpark});
    }
}
