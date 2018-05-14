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
    private BufferedImage bufferedImage;
    private int lineOfShadow = 420;
    private WritableRaster raster;

    private Main() throws IOException {
        count = 0;
        bufferedImage = ImageIO.read(new File("src/image.png"));
        raster = bufferedImage.getRaster();
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
        int obj_x = 100+count;
        int obj_y = 400;
        g.setColor(Color.YELLOW);
        g.fillRect(500, 50, 10, 10);
        g.setColor(Color.GRAY);
        g.drawImage(bufferedImage, obj_x, obj_y, this);
        for(int i = 0; i < raster.getHeight(); i++){
            for(int j = 0; j < raster.getWidth(); j++){
                int[] newVec = {(obj_x + j) - vector[0], (obj_y + i) - vector[1]};
                float r = (float)(newVec[1])/ (lineOfShadow - newVec[1]);
                int[] newVec2 = {(int)(newVec[0]/r), (lineOfShadow - newVec[1])};
                g.drawRect(obj_x+j+(newVec2[0]*2),obj_y+i+(newVec2[1]*2),1,1);
                System.out.println("Obj.y: " + obj_y + "  i: " + i + " newVec2[1]: " + newVec2[1] + " newVec: " + newVec[1]);
  //              break;
           }
    //       break;
        }
        count+=3;
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            System.out.println("Fuck");
        }
        repaint();
    }

    public static void main(String[] args) throws IOException {
        new Main();

    }
}
