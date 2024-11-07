package frc.robot.Arm;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.robot.CoreValues.*;
import frc.robot.CustomChildren.CustomSpark;

public class ArmAngler {
    public CustomSpark armAngleCustomSpark;

    public RelativeEncoder armAngleRelativeEncoder;

    public DutyCycleEncoder armAngleAbsoluteEncoder;
    public double armAngleAbsoluteEncoderOffset;
    public int armAngleAbsoluteEncoderInvert;

    public double armAngleSoftLimit;

    public ArmAngler() {
        armAngleCustomSpark = new CustomSpark(IDs.Constants.armAngleMotor, Inversions.Constants.armAngleMotor);
        armAngleRelativeEncoder = armAngleCustomSpark.relativeEncoder;
        armAngleAbsoluteEncoder = new DutyCycleEncoder(IDs.Constants.armAngleEncoder);

        armAngleAbsoluteEncoderInvert = Inversions.Constants.armAngleEncoder ? -1 : 1;

        armAngleAbsoluteEncoderOffset = Offsets.Constants.armAngleOffset;

        armAngleSoftLimit = Misc.armAngleSoftLimit;
    }

    public void percentageSet(double armAngleSet) {
        if(getAbsoluteAngle() > armAngleSoftLimit && armAngleSet > 0) {
            stop();
        } else {
            armAngleCustomSpark.percentageSet(armAngleSet);
        }
    }

    public void rpmSet(double armAngleRPM) {
        if(getAbsoluteAngle() > armAngleSoftLimit && armAngleRPM > 0) {
            stop();
        } else {
            armAngleCustomSpark.rpmSet(armAngleRPM);
        }
    }

    public void stop() {
        armAngleCustomSpark.stopMotor();
    }

    public double getAbsoluteAngle() {
        double temp = (armAngleAbsoluteEncoderInvert * armAngleAbsoluteEncoder.getAbsolutePosition() * Conversions.Constants.armAngleEncoderDegreesPerRotation - armAngleAbsoluteEncoderOffset);

        while(temp <= 0d) {
            temp += 360d;
        }
        while(temp > 360d) {
            temp -= 360d;
        }

        return temp;
    }

    public void angleSet(double desiredAngle) {
        armAngleCustomSpark.set(armAngleCustomSpark.get() 
            + (Math.abs((desiredAngle - getAbsoluteAngle())) / (Misc.armAngleRange)) > 0.25d 
            ? Math.signum(desiredAngle - getAbsoluteAngle()) * 0.25d 
            : (desiredAngle - getAbsoluteAngle()) / (Misc.armAngleRange));
    }
}
