package frc.trigon.robot.subsystems.ledcorrector;

import edu.wpi.first.wpilibj2.command.*;

public class LEDStripCommands {
    private static final LEDStrip LED_CORRECTOR = LEDStrip.getInstance();

    public static Command getSetLED(int r, int g, int b, int w, int startingLED, int endingLED) {
        return new RunCommand(
                () -> LED_CORRECTOR.setLED(r, g, b, w, startingLED, endingLED),
                LED_CORRECTOR
        );
    }

    public static Command getSetFireAnimation() {
        return new RunCommand(
                LED_CORRECTOR::setFireAnimation,
                LED_CORRECTOR
        );
    }

    public static Command getSetRainbowAnimation() {
        return new RunCommand(
                LED_CORRECTOR::setRainbowAnimation,
                LED_CORRECTOR
        );
    }

    public static Command getSetFlowAnimation(int r, int g, int b, int w) {
        return new RunCommand(
                () -> LED_CORRECTOR.setFlowAnimation(r, g, b, w),
                LED_CORRECTOR
        );
    }
}
