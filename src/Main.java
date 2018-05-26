import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Main extends Frame{

    private static int count;
    private int[] vector = {500, 1};
    private BufferedImage[] bufferedImage;
    private int lineOfShadow;
    private WritableRaster[] raster;

    private Main() throws IOException {
        count = 0;
        for(int i = 0; i < 11; i++) {
            System.out.println("Fuck");
            //bufferedImage[i] = ImageIO.read(new File("src\\images\\horse0" + (i+1) + ".gif"));
            bufferedImage[i] = ImageIO.read(new File("src\\images\\horse01.gif"));
            raster[i] = bufferedImage[i].getRaster();
            System.out.println(raster[i].getMinX());
        }
        lineOfShadow = 400+raster[0].getHeight();
        setResizable(false);
        setVisible(true);
        setSize(2000, 1000);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); }
        });

    }

    @Override
    public void paint(Graphics g) {
        int obj_x = 100+count*6;
        int obj_y = 400;
        g.setColor(Color.PINK);
        g.fillRect(490, 40, 20, 20);
        g.setColor(Color.GRAY);
        g.drawImage(bufferedImage[count], obj_x, obj_y, this);
        for(int i = 0; i < raster[count].getHeight(); i++){
            for(int j = 0; j < raster[count].getWidth(); j++){
                int[] pixel = raster[count].getPixel(j, i, new int[4]);
                if(pixel[3] > 50) {
                    int[] newVec = {(obj_x + j) - vector[0], (obj_y + i)};
                    float r = (float) (newVec[1]) / (lineOfShadow - newVec[1]);
                    int[] newVec2 = {(int) (newVec[0] / r), (lineOfShadow - newVec[1])};
                    g.drawRect((obj_x + j - 1 + (newVec2[0] * 2)), obj_y + i - 1 + (newVec2[1] * 2), 1, 1);
                }
           }
        }
        count++;
        try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            System.out.println("Fuck");
        }
        repaint();
    }

    public static void main(String[] args) throws IOException {
        new Main();

    }
}
