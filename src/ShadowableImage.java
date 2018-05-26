import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShadowableImage {

    private BufferedImage image;

    ShadowableImage(String pathname) throws IOException {

        image = ImageIO.read(new File(pathname));


    }
}
