package desu.nya.desktop.screen;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public class ScreeShotMaker {
    public BufferedImage screenShot(int x, int y, int width, int height) {
        try {
            Rectangle screenRect = new Rectangle(x, y, width, height);
            return new Robot().createScreenCapture(screenRect);
        } catch (AWTException e) {
            e.printStackTrace();
            return null;
        }
    }
}
