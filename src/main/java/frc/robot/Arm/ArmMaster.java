package frc.robot.Arm;

import edu.wpi.first.networktables.DoubleArrayPublisher;
import edu.wpi.first.networktables.DoubleArraySubscriber;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.CoreValues.Controllers;
import frc.robot.CoreValues.Presets;

public class ArmMaster {
    public ArmAngler myArmAngler;
    public Intake myIntake;
    public Middle myMiddle;
    public Launcher myLauncher;

    public DoublePublisher armAngleGetPublisher;
    public DoublePublisher armAngleRPMPublisher;
    public DoublePublisher armAngleAbsoluteAnglePublisher;
    //Ground, bottom, top
    public DoubleArrayPublisher intakeGetsPublisher;
    public DoubleArrayPublisher intakeRPMsPublisher;
    //Back, top
    public DoubleArrayPublisher middleGetsPublisher;
    public DoubleArrayPublisher middleRPMsPublisher;
    //Back, top
    public DoubleArrayPublisher launcherGetsPublisher;
    public DoubleArrayPublisher launcherRPMsPublisher;

    public DoubleSubscriber armAngleSetSubscriber;
    public DoubleArraySubscriber intakeSetsSubscriber;
    public DoubleArraySubscriber middleSetsSubscriber;
    public DoubleArraySubscriber launcherSetsSubscriber;

    public ArmMaster(NetworkTableInstance inst) {
        myArmAngler = new ArmAngler();
        myIntake = new Intake();
        myMiddle = new Middle();
        myLauncher = new Launcher();

        armAngleGetPublisher = inst.getDoubleTopic("rio/ArmMaster/armAngleGet").publish();
        armAngleRPMPublisher = inst.getDoubleTopic("rio/ArmMaster/armAngleRPM").publish();
        armAngleAbsoluteAnglePublisher = inst.getDoubleTopic("rio/ArmMaster/armAngleAbsoluteAngle").publish();
        intakeGetsPublisher = inst.getDoubleArrayTopic("rio/ArmMaster/intakeGets").publish();
        intakeRPMsPublisher = inst.getDoubleArrayTopic("rio/ArmMaster/intakeRPMs").publish();
        middleGetsPublisher = inst.getDoubleArrayTopic("rio/ArmMaster/middleGets").publish();
        middleRPMsPublisher = inst.getDoubleArrayTopic("rio/ArmMaster/middleRPMs").publish();
        launcherGetsPublisher = inst.getDoubleArrayTopic("rio/ArmMaster/launcherGets").publish();
        launcherRPMsPublisher = inst.getDoubleArrayTopic("rio/ArmMaster/launcherRPMs").publish();

        armAngleSetSubscriber = inst.getDoubleTopic("jetson/ArmMaster/armAngleSet").subscribe(0d);
        intakeSetsSubscriber = inst.getDoubleArrayTopic("jetson/ArmMaster/intakeSets").subscribe(new double[]{0d, 0d, 0d});
        middleSetsSubscriber = inst.getDoubleArrayTopic("jetson/ArmMaster/middleSets").subscribe(new double[]{0d, 0d});
        launcherSetsSubscriber = inst.getDoubleArrayTopic("jetson/ArmMaster/launcherSets").subscribe(new double[]{0d, 0d});
    }

    public void stop() {
        myArmAngler.stop();
        myIntake.sparkGroup.combinedStop();
        myMiddle.sparkGroup.combinedStop();
        myLauncher.sparkGroup.combinedStop();
    }

    public void teleopUpdate() {
        if(Controllers.Dynamics.armController.getL1Button()) {
            Robot.armAngleFactor = Math.max(0.05d, Robot.armAngleFactor - 0.005d);
        } else if(Controllers.Dynamics.armController.getL2Button()) {
            Robot.armAngleFactor = Math.min(1d, Robot.armAngleFactor + 0.005d);
        }

        if(Controllers.Dynamics.armController.getR1Button()) {
            Robot.armFireFactor = Math.max(0.05d, Robot.armFireFactor - 0.005d);
        } else if(Controllers.Dynamics.armController.getR2Button()) {
            Robot.armFireFactor = Math.min(1d, Robot.armFireFactor + 0.005d);
        }

        SmartDashboard.putNumber("Fire Factor: ", Robot.armFireFactor);
        SmartDashboard.putNumber("Angle Factor: ", Robot.armAngleFactor);

        double launcherInput = 0d;
        boolean sourceCollecting = false;

        if(Controllers.Dynamics.armController.getCrossButton()) {
            //Amp shooting
            myArmAngler.angleSet(Presets.Constants.ArmAngler.ampAngle);
            launcherInput = Presets.Constants.Launcher.ampLaunch;
        } else if(Controllers.Dynamics.armController.getCircleButton()) {
            //Source collecting
            sourceCollecting = true;
            myArmAngler.angleSet(Presets.Constants.ArmAngler.sourceAngle);
            launcherInput = Presets.Constants.Launcher.sourceIntake;
            myMiddle.sparkGroup.combinedPercentageSet(Presets.Constants.Middle.sourceIntake);
            myIntake.sparkGroup.combinedPercentageSet(new double[]{Presets.Constants.Intake.sourceGround, Presets.Constants.Intake.sourceBack, Presets.Constants.Intake.sourceTop});
        } else if(Controllers.Dynamics.armController.getTriangleButton()) {
            //Rapid firing
            myArmAngler.angleSet(Presets.Constants.ArmAngler.rapidAngle);
            launcherInput = Presets.Constants.Launcher.rapidLaunch;
        } else {
            double angleInput = Math.abs(Controllers.Dynamics.armController.getLeftY()) < Controllers.Constants.armControllerDeadband 
                ? 0d 
                : Controllers.Dynamics.armController.getLeftY() * Robot.armAngleFactor;
            launcherInput = Math.abs(Controllers.Dynamics.armController.getRightY()) < Controllers.Constants.armControllerDeadband 
                ? 0d 
                : -Controllers.Dynamics.armController.getRightY() * Robot.armFireFactor;

            //myArmAngler.RPMSet(angleInput * Constants.maxRPMs.armAngle);
            myArmAngler.percentageSet(angleInput);
        }

        //myLauncher.combinedRPMSet(launcherInput * Constants.maxRPMs.launcher);
        myLauncher.sparkGroup.combinedPercentageSet(launcherInput);

        if(Controllers.Dynamics.armController.getPOV() == 0) {
            //myMiddle.combinedRPMSet(launcherInput * Constants.maxRPMs.middle);
            myMiddle.sparkGroup.combinedPercentageSet(launcherInput);
        } else if(Controllers.Dynamics.armController.getPOV() == 180) {
            myIntake.sparkGroup.combinedPercentageSet(Presets.Constants.Intake.intaking);
            //myMiddle.combinedRPMSet(Math.abs(launcherInput) * Constants.maxRPMs.middle);
            myMiddle.sparkGroup.combinedPercentageSet(Math.abs(launcherInput));
        } else if(Controllers.Dynamics.armController.getPOV() == 270) {
            myIntake.sparkGroup.combinedPercentageSet(Presets.Constants.Intake.outtaking);
            //myMiddle.combinedRPMSet(Presets.Constants.Middle.outtaking * Constants.maxRPMs.middle);
            myMiddle.sparkGroup.combinedPercentageSet(Presets.Constants.Middle.outtaking);
            //myLauncher.combinedRPMSet(Presets.Constants.Launcher.outtaking * Constants.maxRPMs.launcher);
            myMiddle.sparkGroup.combinedPercentageSet(Presets.Constants.Launcher.outtaking);
        } else if(!sourceCollecting) {
            myIntake.sparkGroup.combinedStop();
            myMiddle.sparkGroup.combinedStop();
        }
    }

    public void updateNetwork() {
        myArmAngler.percentageSet(armAngleSetSubscriber.get());
        myIntake.sparkGroup.combinedPercentageSet(intakeSetsSubscriber.get());
        myMiddle.sparkGroup.combinedPercentageSet(middleSetsSubscriber.get());
        myLauncher.sparkGroup.combinedPercentageSet(launcherSetsSubscriber.get());

        armAngleGetPublisher.set(myArmAngler.armAngleCustomSpark.get());
        armAngleRPMPublisher.set(myArmAngler.armAngleRelativeEncoder.getVelocity());
        armAngleAbsoluteAnglePublisher.set(myArmAngler.getAbsoluteAngle());
        intakeGetsPublisher.set(new double[]{myIntake.groundCustomSpark.get(), myIntake.bottomCustomSpark.get(), myIntake.topCustomSpark.get()});
        intakeRPMsPublisher.set(new double[]{myIntake.groundRelativeEncoder.getVelocity(), myIntake.bottomRelativeEncoder.getVelocity(), myIntake.topRelativeEncoder.getVelocity()});
        middleGetsPublisher.set(new double[]{myMiddle.backCustomSpark.get(), myMiddle.frontCustomSpark.get()});
        middleRPMsPublisher.set(new double[]{myMiddle.backRelativeEncoder.getVelocity(), myMiddle.frontRelativeEncoder.getVelocity()});
        launcherGetsPublisher.set(new double[]{myLauncher.backCustomSpark.get(), myLauncher.frontCustomSpark.get()});
        launcherRPMsPublisher.set(new double[]{myLauncher.backRelativeEncoder.getVelocity(), myLauncher.frontRelativeEncoder.getVelocity()});
    }
}
