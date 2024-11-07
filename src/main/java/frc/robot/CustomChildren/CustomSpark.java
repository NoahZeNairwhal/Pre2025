package frc.robot.CustomChildren;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

public class CustomSpark extends CANSparkMax {
    public double maxRPM;
    public RelativeEncoder relativeEncoder;

    public CustomSpark(int id, boolean inverted) {
        this(id, inverted, ChildrenConstants.CustomSpark.defaultMotorType, ChildrenConstants.CustomSpark.defaultMaxRPM, ChildrenConstants.CustomSpark.defaultConversionFactor);
    }

    public CustomSpark(int id, boolean inverted, MotorType type, double maxRPM, double conversionFactor) {
        super(id, type);
        this.maxRPM = maxRPM;
        relativeEncoder = this.getEncoder();

        setInverted(inverted);
        relativeEncoder.setVelocityConversionFactor(conversionFactor);
        relativeEncoder.setPositionConversionFactor(conversionFactor);
    }

    public void percentageSet(double targetPercentage) {
        generalisedSet(ChildrenConstants.CustomSpark.percentageSetLambda, get(), 1d, targetPercentage);
    }

    public void rpmSet(double targetRpm) {
        generalisedSet(ChildrenConstants.CustomSpark.rpmSetLambda, relativeEncoder.getVelocity(), maxRPM, targetRpm);
    }

    //Uses the derivative of the logistic growth equation
    public void generalisedSet(double lambda, double current, double max, double target) {
        //f'(x) = lambda * (f(x) + max) (1 - f(x) / m)
        //lambda is a growth rate constant
        //f(x) is the current value of the function whose value we're changing
        //The + max allows the derivative to produce correct output on the range (-max, max). Without it it would only produce correct output on the range (0, max)
        //m is the target value we want f(x) to equal
        double derivative = lambda * (current + max) * (1 - (current / target));
        //The above produces a value of a derivative, so we have to multiply by a unit of time to get a valid change in the function value
        //20ms is the update rate of the rio
        //(technically speaking the lambda could take care of this, but for comprehension sake I'll multiply by 0.02 here)
        double change = derivative * 0.02;

        //Have to reverse the change if our target is negative, otherwise it will go away from the target value
        if(target > 0) {
            set(current + change);
        } else {
            set(current - change);
        }
    }
}
