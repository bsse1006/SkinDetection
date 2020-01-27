package package123;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Result
{
    private File testFile;
    private double [] [] [] trainedData = new double [256] [256] [256];

    private int r;
    private int b;
    private int g;

    public Result(File testFile, double[][][] trainedData) {
        this.testFile = testFile;
        this.trainedData = trainedData;
        testFileProcessor();
    }

    private void testFileProcessor()
    {
        try {
            // get the BufferedImage, using the ImageIO class
            BufferedImage image;
            image = ImageIO.read(testFile);
            marchThroughImage(image);
            ImageIO.write(image, "jpg", new File("Database/output"+testFile.getName()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void marchThroughImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = image.getRGB(j, i);
                printPixelARGB(pixel);
                if(trainedData[r][g][b]<1.0)
                {
                    image.setRGB(j,i, Color.white.getRGB());
                }
            }
        }
    }

    public void printPixelARGB(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        r = red;
        g = green;
        b = blue;
    }
}
