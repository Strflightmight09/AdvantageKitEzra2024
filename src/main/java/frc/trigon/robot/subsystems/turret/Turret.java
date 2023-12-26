package frc.trigon.robot.subsystems.turret;


import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.util.Units;
import frc.trigon.robot.RobotContainer;
import frc.trigon.robot.subsystems.AbstractSubsystem;
import org.littletonrobotics.junction.Logger;

public class Turret extends AbstractSubsystem {
    private final static Turret INSTANCE = new Turret();
    private final TurretIO turretIO = TurretIO.generateIO();
    private final TurretInputsAutoLogged turretInputs = new TurretInputsAutoLogged();

    public static Turret getInstance() {
        return INSTANCE;
    }

    private Turret() {
    }

    @Override
    public void setBrake(boolean brake) {
        turretIO.setBrake(brake);
    }

    @Override
    public void stop() {
        turretIO.stop();
    }

    @Override
    public void periodic() {
        turretIO.updateInputs(turretInputs);
        Logger.processInputs("Turret", turretInputs);
        updateMechanism();
    }

    void alignToHub() {
        Rotation2d targetAngle = calculateDegreesToHub();
        turretIO.setTargetAngle(limitAngle(targetAngle));
    }

    Rotation2d calculateDegreesToHub() {
        Pose2d currentBluePose = RobotContainer.POSE_ESTIMATOR.getCurrentPose().toCurrentAlliancePose();
        double yDistance = TurretConstants.HUB_POSITION.getY() - currentBluePose.getY();
        double xDistance = TurretConstants.HUB_POSITION.getX() - currentBluePose.getX();
        double targetAngleRadians = Math.atan2(yDistance, xDistance);
        return Rotation2d.fromRadians(targetAngleRadians - currentBluePose.getRotation().getRadians());
    }

    private Rotation2d limitAngle(Rotation2d targetAngle) {
        if (isOverMaximumAngle(targetAngle))
            return targetAngle.minus(Rotation2d.fromDegrees(360));
        else if (isUnderMinimumAngle(targetAngle))
            return targetAngle.plus(Rotation2d.fromDegrees(360));
        return targetAngle;
    }

    private boolean isOverMaximumAngle(Rotation2d targetAngle) {
        return targetAngle.getDegrees() > TurretConstants.ANGLE_MAXIMUM_DEGREES;
    }

    private boolean isUnderMinimumAngle(Rotation2d targetAngle) {
        return targetAngle.getDegrees() < TurretConstants.ANGLE_MINIMUM_DEGREES;
    }

    private Pose3d getTurretPosition() {
        return new Pose3d(new Translation3d(), new Rotation3d(0, 0, Units.degreesToRadians(turretInputs.motorAngleDegrees)));
    }

    private void updateMechanism() {
        TurretConstants.TURRET_LIGAMENT.setAngle(turretInputs.motorAngleDegrees);
        TurretConstants.TARGET_TURRET_POSITION_LIGAMENT.setAngle(turretInputs.profiledTargetPositionDegrees);
        Logger.recordOutput("Poses/Components/TurretPose", getTurretPosition());
        Logger.recordOutput("Mechanisms/TurretMechanism", TurretConstants.TURRET_MECHANISM);
    }
}

