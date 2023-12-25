package frc.trigon.robot.subsystems.turret;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.AutoLogOutput;

public class TurretConstants {
    static final Translation2d HUB_POSITION = new Translation2d(8.248, 4.176);
    static final double
            ANGLE_MAXIMUM_DEGREES = 200,
            ANGLE_MINIMUM_DEGREES = -200;
    private static final double
            TURRET_MECHANISM_WIDTH = 3,
            TURRET_MECHANISM_HEIGHT = 3,
            LIGAMENT_LINE_WIDTH = 10,
            LIGAMENT_LENGTH = 0.5;
    private static final double
            TURRET_MECHANISM_ROOT_X = 1,
            TURRET_MECHANISM_ROOT_Y = 1;
    @AutoLogOutput(key = "Turret/TurretMechanism")
    static final Mechanism2d TURRET_MECHANISM = new Mechanism2d(
            TURRET_MECHANISM_WIDTH,
            TURRET_MECHANISM_HEIGHT
    );
    private static final MechanismRoot2d
            TURRET_MECHANISM_ROOT = TURRET_MECHANISM.getRoot("ZTurretRoot", TURRET_MECHANISM_ROOT_X, TURRET_MECHANISM_ROOT_Y),
            TARGET_TURRET_POSITION_ROOT = TURRET_MECHANISM.getRoot("TargetTurretPosition", TURRET_MECHANISM_ROOT_X, TURRET_MECHANISM_ROOT_Y);
    static final MechanismLigament2d
            TURRET_LIGAMENT = TURRET_MECHANISM_ROOT.append(new MechanismLigament2d("TurretLigament", LIGAMENT_LENGTH, 0, LIGAMENT_LINE_WIDTH, new Color8Bit(Color.kBlue))),
            TARGET_TURRET_POSITION_LIGAMENT = TARGET_TURRET_POSITION_ROOT.append(new MechanismLigament2d("TargetTurretPositionLigament", LIGAMENT_LENGTH, 0, LIGAMENT_LINE_WIDTH, new Color8Bit(Color.kGray)));
}