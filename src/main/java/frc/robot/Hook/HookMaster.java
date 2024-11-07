package frc.robot.Hook;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.networktables.DoubleArrayPublisher;
import edu.wpi.first.networktables.DoubleArraySubscriber;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.CoreValues.*;
import frc.robot.CustomChildren.CustomSpark;
import frc.robot.CustomChildren.CustomSparkGroup;

public class HookMaster {
    public CustomSpark leftCustomSpark;
    public CustomSpark rightCustomSpark;

    public CustomSparkGroup sparkGroup;

    public RelativeEncoder leftRelativeEncoder;
    public RelativeEncoder rightRelativeEncoder;

    public double leftStartPosition;
    public double rightStartPosition;

    //left, right
    public DoubleArrayPublisher hookGetsPublisher;
    public DoubleArrayPublisher hookRPMsPublisher;
    public DoubleArrayPublisher hookPositionsPublisher;

    public DoubleArraySubscriber hookSetsSubscriber;

    public HookMaster(NetworkTableInstance inst) {
        leftCustomSpark = new CustomSpark(IDs.Constants.leftHook, Inversions.Constants.leftHook);
        leftRelativeEncoder = leftCustomSpark.relativeEncoder;
        rightCustomSpark = new CustomSpark(IDs.Constants.rightHook, Inversions.Constants.rightHook);
        rightRelativeEncoder = rightCustomSpark.relativeEncoder;
        leftStartPosition = leftRelativeEncoder.getPosition();
        rightStartPosition = rightRelativeEncoder.getPosition();

        sparkGroup = new CustomSparkGroup(new CustomSpark[]{leftCustomSpark, rightCustomSpark});

        hookGetsPublisher = inst.getDoubleArrayTopic("rio/HookMaster/hookGets").publish();
        hookRPMsPublisher = inst.getDoubleArrayTopic("rio/HookMaster/hookRPMs").publish();
        hookPositionsPublisher = inst.getDoubleArrayTopic("rio/HookMaster/hookPositions").publish();

        hookSetsSubscriber = inst.getDoubleArrayTopic("jetson/HookMaster/hookSets").subscribe(new double[]{0d, 0d});
    }

    public double getLeftMetresExtension() {
        return Math.abs(leftRelativeEncoder.getPosition() - leftStartPosition) * Conversions.Constants.hookMetresPerRotation;
    }

    public double getRightMetresExtension() {
        return Math.abs(rightRelativeEncoder.getPosition() - rightStartPosition) * Conversions.Constants.hookMetresPerRotation;
    }

    public void updateNetwork() {
        sparkGroup.combinedPercentageSet(hookSetsSubscriber.get());

        hookGetsPublisher.set(new double[]{leftCustomSpark.get(), rightCustomSpark.get()});
        hookRPMsPublisher.set(new double[]{leftRelativeEncoder.getVelocity(), rightRelativeEncoder.getVelocity()});
        hookPositionsPublisher.set(new double[]{leftRelativeEncoder.getPosition() - leftStartPosition, rightRelativeEncoder.getPosition() - rightStartPosition});
    }

    public void teleopUpdate() {
        if(Controllers.Dynamics.driveController.getPOV() == 0) {
            sparkGroup.combinedRPMSet(Presets.Constants.Hooks.extending * MaxRPMs.Constants.hook);
        } else if(Controllers.Dynamics.driveController.getPOV() == 180) {
            sparkGroup.combinedRPMSet(Presets.Constants.Hooks.retracting * MaxRPMs.Constants.hook);
        } else {
            sparkGroup.combinedStop();
        }
    }
}