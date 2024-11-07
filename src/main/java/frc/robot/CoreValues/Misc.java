package frc.robot.CoreValues;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PS4Controller;

public class Misc {
    public static final double armAngleSoftLimit = 97.5d;
    public static final double armAngleHardLimit = 100.7d;
    public static final double armAngleRange = 75d;

    public static boolean blue;

    public static void construct() {
        try {
            blue = (DriverStation.getAlliance().get() == DriverStation.Alliance.Blue 
                ? true 
                : (DriverStation.getAlliance().get() == DriverStation.Alliance.Red 
                    ? false 
                    : true));
        } catch(Exception e) {
            e.printStackTrace();
            blue = true;
        }
        
        Controllers.Dynamics.driveController = new PS4Controller(Controllers.Constants.driveControllerPort);
        Controllers.Dynamics.armController = new PS4Controller(Controllers.Constants.armControllerPort);

        if(blue) {
            FieldData.Dynamics.speakerY = FieldData.Constants.blueConstants.speakerY;
            FieldData.Dynamics.ampY = FieldData.Constants.blueConstants.ampY;
            FieldData.Dynamics.trapY1 = FieldData.Constants.blueConstants.trapY1;
            FieldData.Dynamics.trapY2 = FieldData.Constants.blueConstants.trapY2;
            FieldData.Dynamics.trapY3 = FieldData.Constants.blueConstants.trapY3;
            FieldData.Dynamics.rapidY = FieldData.Constants.blueConstants.rapidY;

            FieldData.Dynamics.minSpeakerAngle = FieldData.Constants.blueConstants.minSpeakerAngle;
            FieldData.Dynamics.maxSpeakerAngle = FieldData.Constants.blueConstants.maxSpeakerAngle;
            FieldData.Dynamics.minTrap1Angle = FieldData.Constants.blueConstants.minTrap1Angle;
            FieldData.Dynamics.maxTrap1Angle = FieldData.Constants.blueConstants.maxTrap1Angle;
            FieldData.Dynamics.minTrap2Angle = FieldData.Constants.blueConstants.minTrap2Angle;
            FieldData.Dynamics.maxTrap2Angle = FieldData.Constants.blueConstants.maxTrap2Angle;
            FieldData.Dynamics.minTrap3Angle = FieldData.Constants.blueConstants.minTrap3Angle;
            FieldData.Dynamics.maxTrap3Angle = FieldData.Constants.blueConstants.maxTrap3Angle;
            FieldData.Dynamics.minRapidAngle = FieldData.Constants.blueConstants.minRapidAngle;
            FieldData.Dynamics.maxRapidAngle = FieldData.Constants.blueConstants.maxRapidAngle;
        } else {
            FieldData.Dynamics.speakerY = FieldData.Constants.redConstants.speakerY;
            FieldData.Dynamics.ampY = FieldData.Constants.redConstants.ampY;
            FieldData.Dynamics.trapY1 = FieldData.Constants.redConstants.trapY1;
            FieldData.Dynamics.trapY2 = FieldData.Constants.redConstants.trapY2;
            FieldData.Dynamics.trapY3 = FieldData.Constants.redConstants.trapY3;
            FieldData.Dynamics.rapidY = FieldData.Constants.redConstants.rapidY;

            FieldData.Dynamics.minSpeakerAngle = FieldData.Constants.redConstants.minSpeakerAngle;
            FieldData.Dynamics.maxSpeakerAngle = FieldData.Constants.redConstants.maxSpeakerAngle;
            FieldData.Dynamics.minTrap1Angle = FieldData.Constants.redConstants.minTrap1Angle;
            FieldData.Dynamics.maxTrap1Angle = FieldData.Constants.redConstants.maxTrap1Angle;
            FieldData.Dynamics.minTrap2Angle = FieldData.Constants.redConstants.minTrap2Angle;
            FieldData.Dynamics.maxTrap2Angle = FieldData.Constants.redConstants.maxTrap2Angle;
            FieldData.Dynamics.minTrap3Angle = FieldData.Constants.redConstants.minTrap3Angle;
            FieldData.Dynamics.maxTrap3Angle = FieldData.Constants.redConstants.maxTrap3Angle;
            FieldData.Dynamics.minRapidAngle = FieldData.Constants.redConstants.minRapidAngle;
            FieldData.Dynamics.maxRapidAngle = FieldData.Constants.redConstants.maxRapidAngle;
        }
    }
}
