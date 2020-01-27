package package123;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RGB extends Component
{

    private int r;
    private int b;
    private int g;

    private double [] [] [] skin;
    private double [] [] [] nonSkin;

    public double[][][] getSkin() {
        return skin;
    }

    public double[][][] getNonSkin() {
        return nonSkin;
    }

    public void printPixelARGB(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        r = red;
        g = green;
        b = blue;
        //System.out.println(r+g+b);
    }

    private void marchThroughImage(BufferedImage realImage, BufferedImage maskImage) {
        int w = realImage.getWidth();
        int h = realImage.getHeight();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = maskImage.getRGB(j, i);
                int realPixel = realImage.getRGB(j, i);
                if(pixel == -1)
                {
                    printPixelARGB(realPixel);
                    nonSkin[r][g][b]++;
                }
                else
                {
                    printPixelARGB(realPixel);
                    skin[r][g][b]++;
                }
            }
        }
    }

    public RGB(String real, String mask,double[][][] skin,double[][][] nonSkin) {
        this.skin=skin;
        this.nonSkin=nonSkin;
        try {
            // get the BufferedImage, using the ImageIO class
            BufferedImage realImage, maskImage;
            realImage = ImageIO.read(new File(real));
            maskImage = ImageIO.read(new File(mask));
            marchThroughImage(realImage, maskImage);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}