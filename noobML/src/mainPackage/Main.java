package mainPackage;

import package123.FolderToFiles;
import package123.RGB;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] foo) {
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
            //RGB rgb = new RGB("/home/yasinsazid/Desktop/ibtd/"+real.getName());
            //RGB rgb1 = new RGB("/home/yasinsazid/Desktop/ibtd/"+mask.getName());
            doneFiles.add(real);
            doneFiles.add(mask);
            System.out.println(real.getName());
            System.out.println(mask.getName());
        }
    }
}
