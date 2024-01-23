package frc.trigon.robot.subsystems.turret.toohardturret;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.trigon.robot.subsystems.turret.TurretIO;
import frc.trigon.robot.subsystems.turret.TurretInputsAutoLogged;
import frc.trigon.robot.utilities.Conversions;

public class ToohardTurretIO extends TurretIO {
    private final TalonFX motor = ToohardTurretConstants.MOTOR;
    private final StatusSignal<Double> MOTOR_VOLTAGE_STATUS_SIGNAL = motor.getMotorVoltage();
    private final MotionMagicVoltage positionRequest = new MotionMagicVoltage(0).withEnableFOC(ToohardTurretConstants.FOC_ENABLED);

    @Override
    protected void updateInputs(TurretInputsAutoLogged inputs) {
        inputs.motorVoltage = MOTOR_VOLTAGE_STATUS_SIGNAL.refresh().getValue();
        inputs.motorAngleDegrees = getMotorAngleDegrees();
        inputs.motorVelocityDegreesPerSecond = getMotorVelocityDegreesPerSecond();
        inputs.profiledTargetPositionDegrees = positionRequest.Position;
    }

    @Override
    protected void setTargetAngle(Rotation2d targetAngle) {
        motor.setControl(positionRequest.withPosition(targetAngle.getRotations()));
    }

    @Override
    protected void stop() {
        motor.stopMotor();
    }

    @Override
    protected void setBrake(boolean brake) {
        if (brake)
            motor.setNeutralMode(NeutralModeValue.Brake);
        else
            motor.setNeutralMode(NeutralModeValue.Coast);
    }

    private double getMotorAngleDegrees() {
        return Conversions.revolutionsToDegrees(ToohardTurretConstants.ENCODER_POSITION_SIGNAL.refresh().getValue());
    }

    private double getMotorVelocityDegreesPerSecond() {
        return Conversions.revolutionsToDegrees(ToohardTurretConstants.ENCODER_VELOCITY_SIGNAL.refresh().getValue());
    }
}
