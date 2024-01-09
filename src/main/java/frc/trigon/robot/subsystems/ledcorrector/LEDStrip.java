package frc.trigon.robot.subsystems.ledcorrector;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.ColorFlowAnimation;
import com.ctre.phoenix.led.FireAnimation;
import com.ctre.phoenix.led.RainbowAnimation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDStrip extends SubsystemBase {
    private final static LEDStrip INSTANCE = new LEDStrip();

    private final CANdle candle = LEDStripConstants.CANDLE;

    public static LEDStrip getInstance() {
        return INSTANCE;
    }

    private LEDStrip() {
    }

    public void setLED(int r, int g, int b, int w, int startingLED, int endingLED) {
        candle.setLEDs(r, g, b, w, startingLED, endingLED - startingLED + 1);
    }

    public void setFireAnimation() {
        candle.animate(
                new FireAnimation(
                        1,
                        1,
                        45,
                        1,
                        1,
                        false,
                        0
                )
        );
    }

    public void setFireAnimation(double brightness, int speed, double sparking, double cooling, boolean backwards, int offset) {
        candle.animate(
                new FireAnimation(
                        brightness,
                        speed,
                        45,
                        sparking,
                        cooling,
                        backwards,
                        offset
                )
        );
    }

    public void setRainbowAnimation() {
        candle.animate(
                new RainbowAnimation(
                        1,
                        1,
                        45,
                        false,
                        0
                )
        );
    }

    public void setRainbowAnimation(double brightness, int speed, boolean backwards,int offset) {
        candle.animate(
                new RainbowAnimation(
                        brightness,
                        speed,
                        45,
                        backwards,
                        offset
                )
        );
    }

    public void setFlowAnimation(int r, int g, int b, int w) {
        candle.animate(new ColorFlowAnimation(
                r,
                g,
                b,
                w,
                1,
                45,
                ColorFlowAnimation.Direction.Forward,
                0
        ));
    }
    public void setFlowAnimation(int r, int g, int b, int w, int speed, ColorFlowAnimation.Direction direction, int offset) {
        candle.animate(new ColorFlowAnimation(
                r,
                g,
                b,
                w,
                speed,
                45,
                direction,
                offset
        ));
    }
}
