package frc.robot.CustomChildren;

public class CustomSparkGroup {
    public CustomSpark[] customSparks;

    public CustomSparkGroup(CustomSpark[] customSparks) {
        this.customSparks = customSparks;
    }

    public void combinedPercentageSet(double targetPercentage) {
        for(int i = 0; i < customSparks.length; ++i) {
            customSparks[i].percentageSet(targetPercentage);
        }
    }

    public void combinedPercentageSet(double[] targetPercentages) {
        for(int i = 0; i < customSparks.length; ++i) {
            customSparks[i].percentageSet(targetPercentages[i]);
        }
    }

    public void combinedRPMSet(double targetRPM) {
        for(int i = 0; i < customSparks.length; ++i) {
            customSparks[i].rpmSet(targetRPM);
        }
    }

    public void combinedRPMSet(double[] targetRPMs) {
        for(int i = 0; i < customSparks.length; ++i) {
            customSparks[i].rpmSet(targetRPMs[i]);
        }
    }

    public void combinedStop() {
        for(int i = 0; i < customSparks.length; ++i) {
            customSparks[i].stopMotor();
        }
    }
}
