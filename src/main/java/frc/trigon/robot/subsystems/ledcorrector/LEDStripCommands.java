package frc.trigon.robot.subsystems.ledcorrector;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class LEDStripCommands {
    private static final LEDStrip LED_CORRECTOR = LEDStrip.getInstance();

    public static Command getSetLED(int r, int g, int b, int w, int startingLED, int endingLED) {
        return new FunctionalCommand(
                LED_CORRECTOR::cutTheLights,
                () -> LED_CORRECTOR.setLED(r, g, b, w, startingLED, endingLED),
                (interrupted) -> {
                },
                () -> false,
                LED_CORRECTOR
        );
    }

    public static Command getSetFireAnimation() {
        return new FunctionalCommand(
                LED_CORRECTOR::cutTheLights,
                LED_CORRECTOR::setFireAnimation,
                (interrupted) -> {
                },
                () -> false,
                LED_CORRECTOR
        );
    }

    public static Command getSetRainbowAnimation() {
        return new FunctionalCommand(
                LED_CORRECTOR::cutTheLights,
                LED_CORRECTOR::setRainbowAnimation,
                (interrupted) -> {
                },
                () -> false,
                LED_CORRECTOR
        );
    }

    public static Command getSetFlowAnimation(int r, int g, int b, int w) {
        return new FunctionalCommand(
                LED_CORRECTOR::cutTheLights,
                () -> LED_CORRECTOR.setFlowAnimation(r, g, b, w),
                (interrupted) -> {
                },
                () -> false,
                LED_CORRECTOR
        );
    }

    public static Command getCutTheLights() {
        return new RunCommand(
                LED_CORRECTOR::cutTheLights,
                LED_CORRECTOR
        );
    }
}
