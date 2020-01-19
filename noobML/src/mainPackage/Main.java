package mainPackage;

import package123.FolderToFiles;
import package123.ImageProcessor;
import package123.RGB;
import package123.Result;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] foo) {

        double [] [] [] skin = new double [256] [256] [256];
        double [] [] [] nonSkin = new double [256] [256] [256];

        File[] listOfFiles = FolderToFiles.getListOfFiles("/home/yasinsazid/Desktop/ibtd");

        List<File> doneFiles = new ArrayList<>();

        for (File file : listOfFiles)
        {
            if(doneFiles.contains(file))
                continue;

            File real = null;
            File mask = null;
            if (file.isFile()&&file.getName().contains("bmp"))
            {
                mask = file;

                for (File otherFile : listOfFiles)
                {
                    if(otherFile.isFile()&&otherFile.getName().contains("jpg")&&otherFile.getName().contains(
                            file.getName().substring(0,4)))
                    {
                        real = otherFile;
                    }
                }
            }
            else if (file.isFile()&&file.getName().contains("jpg"))
            {
                real = file;

                for (File otherFile : listOfFiles)
                {
                    if(otherFile.isFile()&&otherFile.getName().contains("bmp")&&otherFile.getName().contains(
                            file.getName().substring(0,4)))
                    {
                        mask = otherFile;
                    }
                }
            }

            RGB rgb = new RGB("/home/yasinsazid/Desktop/ibtd/"+real.getName(),
                    "/home/yasinsazid/Desktop/ibtd/"+mask.getName(),skin,nonSkin);

            doneFiles.add(real);
            doneFiles.add(mask);
        }

        ImageProcessor imageProcessor = new ImageProcessor(skin,nonSkin);

        Result result = new Result("photos/s.jpg", imageProcessor.getTrainedData());
    }
}
