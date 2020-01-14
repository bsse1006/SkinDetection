package package123;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RGB extends Component {

    private File[] listOfFiles = null;
    private int r;
    private int b;
    private int g;

    public int getR() {
        return r;
    }

    public int getB() {
        return b;
    }

    public int getG() {
        return g;
    }

    public void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        r = red;
        g = green;
        b = blue;
        //System.out.println(r+g+b);
    }

    private void marchThroughImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = image.getRGB(j, i);
                printPixelARGB(pixel);
            }
        }
    }

    public RGB(String filePath) {
        try {
            // get the BufferedImage, using the ImageIO class
            BufferedImage image;
            image = ImageIO.read(new File(filePath));
            marchThroughImage(image);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}