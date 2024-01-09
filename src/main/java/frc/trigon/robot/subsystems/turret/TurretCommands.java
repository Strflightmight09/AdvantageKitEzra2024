package frc.trigon.robot.subsystems.turret;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class TurretCommands {
    private static final Turret TURRET = Turret.getInstance();

    public static Command getAlignToHubCommand() {
        return new RunCommand(
                () -> TURRET.alignToHub(),
                TURRET
        );
    }
}